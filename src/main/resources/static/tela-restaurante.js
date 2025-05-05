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
        }

        restaurantes.forEach(restaurante => {
            console.log(`Renderizando restaurante: ${restaurante.nome}`);
            const item = document.createElement("li");
            item.innerHTML = `
                <span>${restaurante.nome}</span>
                <button onclick="editarCardapio(${restaurante.id})">Editar Cardápio</button>
            `;
            listaRestaurantes.appendChild(item);
        });

    } catch (error) {
        console.error("Erro ao carregar restaurantes:", error);
    }
}

window.onload = carregarRestaurantes;