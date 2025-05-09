document.getElementById("loginForm").addEventListener("submit", async function(event) {
    event.preventDefault();

    const email = document.getElementById("email").value;
    const senha = document.getElementById("senha").value;

    try {
        const response = await fetch("/usuario/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, senha }) // ✅ Agora enviamos apenas email e senha
        });

        if (!response.ok) {
            alert("Credenciais inválidas. Tente novamente!");
            return;
        }

        const data = await response.json();
        console.log("Dados recebidos do backend:", data); // ✅ Log para depuração

		if (!response.ok) { // ✅ Agora só tratamos erro se a resposta da API realmente falhar
		    alert("Erro no login: " + (data.mensagem || "Falha desconhecida"));
		    console.warn("Erro na autenticação:", response.status);
		    return;
		}

		if (!data.tipo_user) { // ✅ Agora apenas alertamos se `tipo_user` não estiver na resposta
		    console.warn("Aviso: Tipo de usuário não veio corretamente.", data);
		}

        // ✅ Salvamos `tipo_user` corretamente na sessão
        sessionStorage.setItem("usuarioId", data.id);
        sessionStorage.setItem("tipo_user", data.tipo_user);
		console.log("Cliente ID recebido do backend:", data.clienteId); 
		sessionStorage.setItem("usuarioId", data.clienteId);
		console.log("Cliente ID salvo na sessão:", sessionStorage.getItem("usuarioId"));

        // ✅ Agora garantimos que o redirecionamento seja baseado no tipo correto
        if (data.tipo_user === "cliente") {
            window.location.href = "tela-cliente.html";
        } else if (data.tipo_user === "restaurante") {
            window.location.href = "tela-restaurante.html";
        } else {
            alert("Tipo de usuário inválido!");
        }

    } catch (error) {
        console.error("Erro no login:", error);
        alert("Erro inesperado ao tentar logar.");
    }


});
