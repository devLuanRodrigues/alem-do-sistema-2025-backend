function deleteClient(button) {
    const clientId = button.getAttribute('data-client-id');

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

function searchClients() {
    const name = document.getElementById("searchName").value;
    const cpf = document.getElementById("searchCpf").value;

    let url = '/clients/search?';
    if (name) {
        url += `nome=${encodeURIComponent(name)}&`;
    }
    if (cpf) {
        url += `cpf=${encodeURIComponent(cpf)}&`;
    }

    fetch(url)
        .then(response => {
            if(!response.ok) {
                throw new Error('Erro ao buscar clientes');
            }
            return response.json();
        })
        .then(clients => {
            const tableBody = document.getElementById("clientTableBody");
            tableBody.innerHTML = '';

            clients.forEach(client => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${client.cpf}</td>
                    <td>${client.nome}</td>
                    <td>${client.contato[0] ? client.contato[0].valorContato : 'N/A'}</td>
                    <td>
                        <a href="/clients/${client.id}" class="btn btn-primary">Perfil</a>
                        <button class="btn btn-danger" th:data-client-id="${client.id}" onclick="deleteClient(this)">Excluir</button>
</td>
                `;
                tableBody.appendChild(row);
            })
        })
        .catch(error => {
            console.error(error);
            alert('Erro ao buscar clientes.');
        });
}