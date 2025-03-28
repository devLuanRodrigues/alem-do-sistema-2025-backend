function deleteClient(button) {
    const clientId = button.getAttribute('data-client-id');
    console.log("Client ID capturado:", clientId); // Depuração

    if (!clientId || clientId === "undefined") {
        alert("Erro: ID do cliente não encontrado!");
        return;
    }

    if (confirm('Tem certeza que deseja excluir este cliente?')) {
        fetch(`/clients/${clientId}`, { method: 'DELETE' })
            .then(response => {
                if (response.ok) {
                    location.reload();
                } else {
                    return response.text().then(err => { throw new Error(err); });
                }
            })
            .catch(error => alert('Erro ao excluir cliente: ' + error.message));
    }
}


function addContact(){
    alert('Função ainda não implementada!');
}