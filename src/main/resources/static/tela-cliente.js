let carrinho = JSON.parse(localStorage.getItem('carrinho')) || [];
let itemSelecionado = null;
let pedidoEmAndamento = null;
let restauranteAtualId = null;

// Event Listeners
document.getElementById("verRestaurantesBtn")?.addEventListener("click", async function(event) {
    event.preventDefault();
    await carregarRestaurantes();
});


document.getElementById("verCarrinhoBtn")?.addEventListener("click", mostrarCarrinho);

document.getElementById("sairBtn")?.addEventListener("click", async function(event) {
    event.preventDefault();
    try {
        const response = await fetch("/usuario/logout", { method: "GET" });
        if (response.ok) {
            sessionStorage.clear();
            localStorage.clear();
            window.location.href = "login.html";
        } else {
            throw new Error('Erro ao sair');
        }
    } catch (error) {
        console.error("Erro:", error);
        alert("Erro ao sair.");
    }
});

// Funções principais
async function carregarRestaurantes() {
    try {
        const response = await fetch("/restaurantes");
        if (!response.ok) throw new Error(`Erro: ${response.statusText}`);

        const restaurantes = await response.json();
        
        const listaRestaurantes = document.getElementById("lista-restaurantes") || 
                                 document.createElement("div");
        
        listaRestaurantes.innerHTML = restaurantes.length > 0 
            ? restaurantes.map(r => `
                <div class="restaurante">
                    <div class="restaurante-info">
                        <strong>${r.nome}</strong>
                        <p>${r.endereco}</p>
                    </div>
                    <button class="btn-cardapio" onclick="verCardapio(${r.id})">Ver Cardápio</button>
                </div>
            `).join("")
            : `<p>Nenhum restaurante disponível.</p>`;

        document.getElementById("conteudo").innerHTML = `
            <h1>Restaurantes Disponíveis</h1>
            <div id="lista-restaurantes">
                ${listaRestaurantes.innerHTML}
            </div>
        `;

    } catch (error) {
        console.error("Erro:", error);
        alert("Erro ao carregar restaurantes.");
    }
}

async function verCardapio(restauranteId) {
    try {
        restauranteAtualId = restauranteId;
        sessionStorage.setItem('restauranteAtual', restauranteId);
        
        const response = await fetch(`/restaurantes/${restauranteId}/cardapio`);
        if (!response.ok) throw new Error(`Erro: ${response.statusText}`);

        const cardapio = await response.json();
        
        let cardapioHtml = cardapio.length > 0 
            ? cardapio.map(item => `
                <div class="item-cardapio">
                    <div class="item-info">
                        <strong>${item.nome}</strong>
                        <p>${item.descricao}</p>
                        <span>R$ ${item.preco.toFixed(2)}</span>
                    </div>
                    <button class="btn-pedido" onclick="selecionarItem(${item.id_item}, ${item.preco}, '${item.nome.replace("'", "\\'")}')">
                        Fazer Pedido
                    </button>
                </div>
            `).join("")
            : `<p>Cardápio vazio.</p>`;

        document.getElementById("conteudo").innerHTML = `
            <h1>Cardápio do Restaurante</h1>
            <button class="btn-voltar" onclick="carregarRestaurantes()">Voltar</button>
            <div id="lista-cardapio">
                ${cardapioHtml}
            </div>
        `;

    } catch (error) {
        console.error("Erro:", error);
        alert("Erro ao carregar cardápio.");
    }
}

function selecionarItem(itemId, preco, nome) {
    itemSelecionado = { id: itemId, preco, nome };
    document.getElementById('modalItemInfo').innerHTML = `
        <p><strong>${nome}</strong></p>
        <p>Preço unitário: R$ ${preco.toFixed(2)}</p>
    `;
    document.getElementById('quantidade').value = 1;
    document.getElementById('quantidadeModal').style.display = 'block';
}

function fecharModal() {
    document.getElementById('quantidadeModal').style.display = 'none';
}

function adicionarAoCarrinho() {
    const quantidade = parseInt(document.getElementById('quantidade').value);
    if (quantidade < 1) {
        alert("Quantidade inválida!");
        return;
    }

    const item = {
        ...itemSelecionado,
        quantidade,
        total: itemSelecionado.preco * quantidade
    };

    carrinho.push(item);
    localStorage.setItem('carrinho', JSON.stringify(carrinho));
    fecharModal();
    alert("Item adicionado ao carrinho!");
}

function mostrarCarrinho() {
    if (carrinho.length === 0) {
        document.getElementById("conteudo").innerHTML = `
            <h1>Meu Carrinho</h1>
            <p>Carrinho vazio</p>
            <button class="btn-voltar" onclick="carregarRestaurantes()">Voltar</button>
        `;
        return;
    }

    let carrinhoHtml = carrinho.map((item, index) => `
        <div class="item-carrinho">
            <div class="item-info">
                <strong>${item.nome}</strong>
                <p>${item.quantidade} x R$ ${item.preco.toFixed(2)} = R$ ${item.total.toFixed(2)}</p>
            </div>
            <button class="btn-remover" onclick="removerItem(${index})">Remover</button>
        </div>
    `).join("");

    const totalPedido = carrinho.reduce((sum, item) => sum + item.total, 0);

    document.getElementById("conteudo").innerHTML = `
        <h1>Meu Carrinho</h1>
        <div id="lista-carrinho">
            ${carrinhoHtml}
        </div>
        <div class="total-pedido">
            <strong>Total: R$ ${totalPedido.toFixed(2)}</strong>
        </div>
        <button class="btn-finalizar" onclick="finalizarPedido()">Finalizar Pedido</button>
        <button class="btn-voltar" onclick="carregarRestaurantes()">Voltar</button>
    `;
}

function removerItem(index) {
    carrinho.splice(index, 1);
    localStorage.setItem('carrinho', JSON.stringify(carrinho));
    mostrarCarrinho();
}

async function finalizarPedido() {
    try {
        // Validações iniciais
        if (carrinho.length === 0) {
            alert("Seu carrinho está vazio!");
            return;
        }

        // Obter IDs necessários
        const clienteId = sessionStorage.getItem('usuarioId');
        if (!clienteId) {
            alert("Você precisa estar logado para finalizar o pedido!");
            window.location.href = "login.html";
            return;
        }

        // Verifica o restaurante
        const restauranteId = restauranteAtualId || sessionStorage.getItem('restauranteAtual');
        if (!restauranteId) {
            alert("Não foi possível identificar o restaurante. Por favor, selecione novamente.");
            return;
        }

        // Calcula o total
        const totalPedido = carrinho.reduce((sum, item) => sum + item.total, 0);

        // Prepara o objeto do pedido
        pedidoEmAndamento = {
            idCliente: Number(clienteId),
            idRestaurante: Number(restauranteId),
            itens: carrinho.map(item => ({
                idItemCardapio: item.id,
                quantidade: item.quantidade,
                precoUnitario: item.preco,
                observacao: item.observacao || ''
            })),
            valorTotal: totalPedido,
            dataPedido: new Date().toISOString()
        };

        // Abre o modal de pagamento
        document.getElementById('pagamentoModal').style.display = 'block';
        atualizarDetalhesPagamento();

    } catch (error) {
        console.error("Erro ao preparar pedido:", error);
        alert("Erro ao preparar pedido. Por favor, tente novamente.");
    }
}

function fecharPagamentoModal() {
    document.getElementById('pagamentoModal').style.display = 'none';
}

function atualizarDetalhesPagamento() {
    const formaSelecionada = document.querySelector('input[name="formaPagamento"]:checked').value;
    const detalhesDiv = document.getElementById('detalhesPagamento');
    
    switch(formaSelecionada) {
        case 'PIX':
            detalhesDiv.innerHTML = `
                <h3>Pagamento via PIX</h3>
                <p>Você receberá um QR Code para efetuar o pagamento</p>
            `;
            break;
            
        case 'CARTAO':
            detalhesDiv.innerHTML = `
                <h3>Cartão de Crédito</h3>
                <input type="text" id="numeroCartao" placeholder="Número do Cartão" required>
                <input type="text" id="validade" placeholder="MM/AA" required>
                <input type="text" id="cvv" placeholder="CVV" required>
                <input type="text" id="nomeTitular" placeholder="Nome do Titular" required>
            `;
            break;
            
        case 'DINHEIRO':
            detalhesDiv.innerHTML = `
                <h3>Pagamento em Dinheiro</h3>
                <input type="number" id="trocoPara" placeholder="Valor para troco (opcional)" min="0" step="0.01">
            `;
            break;
    }
}

document.querySelectorAll('input[name="formaPagamento"]').forEach(radio => {
    radio.addEventListener('change', atualizarDetalhesPagamento);
});

async function confirmarPagamento() {
    try {
        const formaPagamento = document.querySelector('input[name="formaPagamento"]:checked').value;
        
        // Adiciona detalhes de pagamento conforme o tipo
        const detalhesPagamento = {};
        switch(formaPagamento) {
            case 'CARTAO':
                detalhesPagamento.numeroCartao = document.getElementById('numeroCartao').value;
                detalhesPagamento.validade = document.getElementById('validade').value;
                detalhesPagamento.cvv = document.getElementById('cvv').value;
                detalhesPagamento.nomeTitular = document.getElementById('nomeTitular').value;
                break;
            case 'DINHEIRO':
                const trocoPara = parseFloat(document.getElementById('trocoPara').value);
                if (!isNaN(trocoPara) && trocoPara > 0) {
                    detalhesPagamento.trocoPara = trocoPara;
                }
                break;
        }
		pedidoEmAndamento.idCliente = sessionStorage.getItem("usuarioId"); // ✅ Garante que ID do cliente seja incluído
		Object.assign(pedidoEmAndamento, {
		    formaPagamento,
		    detalhesPagamento
		});
        // Completa o objeto do pedido
        Object.assign(pedidoEmAndamento, {
            formaPagamento,
            detalhesPagamento
        });

        // Mostra loading
        document.getElementById('pagamentoModal').innerHTML = `
            <div class="modal-content">
                <h2>Processando seu pedido...</h2>
                <div class="spinner"></div>
            </div>
        `;

        // Envia para o backend
        const response = await fetch('/pedidos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem('token') || ''}`
            },
            body: JSON.stringify(pedidoEmAndamento)
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || 'Erro ao finalizar pedido');
        }

        // Limpeza e feedback
        localStorage.removeItem('carrinho');
        carrinho = [];
        pedidoEmAndamento = null;
        restauranteAtualId = null;
        sessionStorage.removeItem('restauranteAtual');
        
        document.getElementById('pagamentoModal').style.display = 'none';
        mostrarCarrinho();
        
        const pedidoResponse = await response.json();
        alert(`Pedido #${pedidoResponse.id} realizado com sucesso!`);

    } catch (error) {
        console.error("Erro no pagamento:", error);
        document.getElementById('pagamentoModal').style.display = 'none';
        alert("Falha no pagamento: " + error.message);
    }
}

window.onload = function() {
    const usuarioId = sessionStorage.getItem('usuarioId');
    const tipoUsuario = sessionStorage.getItem('tipo_user'); // ✅ Agora buscando `tipo_user` corretamente

    if (!usuarioId || !tipoUsuario) { // ✅ Agora verificamos ambos corretamente
        alert("Erro: Você precisa estar logado para acessar esta página.");
        window.location.href = "login.html";
        return;
    }

    console.log("Usuário ID carregado:", usuarioId);
    console.log("Tipo de usuário carregado:", tipoUsuario);
};
