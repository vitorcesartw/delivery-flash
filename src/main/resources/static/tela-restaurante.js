async function carregarRestaurantes() {
    try {
        const usuarioId = sessionStorage.getItem("usuarioId");

        if (!usuarioId || isNaN(usuarioId)) {
            console.error("Erro: ID do usuário não encontrado ou inválido!", usuarioId);
            return;
        }

        console.log("Usuário ID recuperado da sessão:", usuarioId);

        const response = await fetch(`/restaurantes/vinculados?usuarioId=${usuarioId}`);
        const restaurantes = await response.json();

        console.log("Restaurantes carregados do backend:", restaurantes);

        const listaRestaurantes = document.getElementById("listaRestaurantes");

        if (!listaRestaurantes) {
            console.error("Erro: Elemento #listaRestaurantes não encontrado no DOM!");
            return;
        }

        listaRestaurantes.innerHTML = "";

        if (restaurantes.length === 0) {
            console.warn("Nenhum restaurante vinculado encontrado!");
            listaRestaurantes.innerHTML = "<li>Nenhum restaurante cadastrado</li>";
        }

        restaurantes.forEach(restaurante => {
            console.log(`Renderizando restaurante: ${restaurante.nome}`);
            const item = document.createElement("li");
            item.innerHTML = `
                <div class="restaurante-info">
                    <span>${restaurante.nome}</span>
                    <div class="restaurante-actions">
                        <button onclick="editarCardapio(${restaurante.id})">Editar Cardápio</button>
                        <button onclick="cadastrarItemCardapio(${restaurante.id})">Cadastrar Item</button>
                    </div>
                </div>
            `;
            listaRestaurantes.appendChild(item);
        });

    } catch (error) {
        console.error("Erro ao carregar restaurantes:", error);
        alert("Erro ao carregar restaurantes. Tente novamente mais tarde.");
    }
}

function editarCardapio(restauranteId) {
    // Implemente a lógica para editar cardápio
    console.log(`Editando cardápio do restaurante ID: ${restauranteId}`);
    // window.location.href = `editar-cardapio.html?restauranteId=${restauranteId}`;
}

function cadastrarItemCardapio(restauranteId) {
    // Redireciona para a página de cadastro de item no cardápio
    console.log(`Cadastrando item para restaurante ID: ${restauranteId}`);
    sessionStorage.setItem("restauranteAtual", restauranteId);
    window.location.href = "item-cardapio.html";
}

// Carrega os restaurantes quando a página é aberta
window.onload = carregarRestaurantes;