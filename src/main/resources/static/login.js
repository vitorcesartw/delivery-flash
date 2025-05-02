document.getElementById("loginForm").addEventListener("submit", async function(event) {
    event.preventDefault();

    const email = document.getElementById("email").value;
    const senha = document.getElementById("senha").value;
    const tipoUsuario = document.getElementById("tipoUsuario").value;

    try {
        const response = await fetch("/usuario/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, senha, tipoUsuario })
        });

        if (!response.ok) {
            alert("Credenciais inválidas. Tente novamente!");
            return;
        }

        const data = await response.json();
        console.log("Dados recebidos do backend:", data); // ⚠ Log para depuração

        if (!data.id || !data.tipoUsuario) {
            console.error("Erro: ID do usuário ou tipo inválido!", data);
            alert("Erro ao identificar usuário.");
            return;
        }

        // ⚠ Agora garantimos que `usuarioId` seja salvo corretamente na sessão
        sessionStorage.setItem("usuarioId", data.id);
        sessionStorage.setItem("tipoUsuario", data.tipoUsuario);
        console.log("Usuário ID salvo:", sessionStorage.getItem("usuarioId"));

        // ⚠ Redirecionamos com base no tipo de usuário
        if (data.tipoUsuario === "cliente") {
            window.location.href = "tela-cliente.html";
        } else if (data.tipoUsuario === "restaurante") {
            window.location.href = "tela-restaurante.html";
        } else {
            alert("Tipo de usuário inválido!");
        }

    } catch (error) {
        console.error("Erro no login:", error);
        alert("Erro inesperado ao tentar logar.");
    }
});