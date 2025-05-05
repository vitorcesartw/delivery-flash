// Adicione estas variáveis no início do script
let pedidoEmAndamento = null;

// Modifique a função finalizarPedido para abrir o modal de pagamento
async function finalizarPedido() {
    if (carrinho.length === 0) {
        alert("Seu carrinho está vazio!");
        return;
    }

    // Calcula o total
    const totalPedido = carrinho.reduce((sum, item) => sum + item.total, 0);
    
    // Prepara o objeto do pedido
    pedidoEmAndamento = {
        itens: carrinho.map(item => ({
            idItemCardapio: item.id,
            quantidade: item.quantidade
        })),
        valorTotal: totalPedido
    };

    // Abre o modal de pagamento
    document.getElementById('pagamentoModal').style.display = 'block';
    atualizarDetalhesPagamento();
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
                <p>Prepare o valor exato para a entrega</p>
            `;
            break;
    }
}

// Adicione este listener para atualizar dinamicamente os detalhes
document.querySelectorAll('input[name="formaPagamento"]').forEach(radio => {
    radio.addEventListener('change', atualizarDetalhesPagamento);
});

async function confirmarPagamento() {
    const formaPagamento = document.querySelector('input[name="formaPagamento"]:checked').value;
    
    try {
        // Adiciona a forma de pagamento ao pedido
        pedidoEmAndamento.formaPagamento = formaPagamento;
        
        // Envia o pedido para o backend
        const response = await fetch('/pedidos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem('token')}`
            },
            body: JSON.stringify(pedidoEmAndamento)
        });

        if (response.ok) {
            // Limpa o carrinho e fecha o modal
            localStorage.removeItem('carrinho');
            carrinho = [];
            pedidoEmAndamento = null;
            
            fecharPagamentoModal();
            mostrarCarrinho();
            
            alert("Pedido finalizado com sucesso! Aguarde a confirmação.");
        } else {
            throw new Error('Erro ao finalizar pedido');
        }
    } catch (error) {
        console.error("Erro:", error);
        alert("Erro ao processar pagamento: " + error.message);
    }
}