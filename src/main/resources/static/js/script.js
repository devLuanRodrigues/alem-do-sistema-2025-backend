function addClient(event){
    event.preventDefault();

    const name = document.getElementById("clientName").value;
    const cpf = document.getElementById("clientCpf").value;
    let birthDate = document.getElementById("clientBirthDate").value;
    const address = document.getElementById("clientAddress").value;

    birthDate = birthDate.split("-").reverse().join("/");

    const contacts = Array.from(document.querySelectorAll(".contact-item")).map(item => {
        return {
            tipoContato: item.querySelector(".contactType").value,
            valorContato: item.querySelector(".contactValue").value,
            observacao: item.querySelector(".contactObservation").value
        };
    });

    const clientData = {
      nome: name,
      cpf: cpf,
      dataNascimento: birthDate,
      endereco: address,
      contato: contacts
    };

    fetch('http://localhost:8080/clients', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify((clientData))
    })
        .then(response => {
            console.log(response)
            console.log(clientData)
            if (!response.ok) {
                throw new Error('Erro ao cadastrar cliente');
            }
            return response.json();
        })
        .then(data => {
            alert('Cliente cadastrado com sucesso!');
            document.getElementById("clientForm").reset();
            document.getElementById("contactList").innerHTML = '';
            closeModal();
        })
        .catch(error => {
            console.error(error);
            alert('Erro ao cadastrar cliente!')
        });
}

function addContact() {
    const contactList = document.getElementById("contactList");
    const contactItem = document.createElement("div");
    contactItem.className = "contact-item";
    contactItem.innerHTML = `
        <select class="contactType" required>
            <option value="" disabled selected>Tipo de Contato</option>
            <option value="TELEFONE">Telefone</option>
            <option value="EMAIL">Email</option>
        </select>
        <input type="text" class="contactValue" placeholder="Valor do Contato" required/>
        <input type="text" class="contactObservation" placeholder="Observação"/>
        <button type="button" class="btn btn-danger" onclick="removeContact(this)">Remover</button>
    `;

    const separator = document.createElement("div");
    separator.className = "contact-separator";

    contactList.appendChild(contactItem);
}

function toggleContactForm() {
    const form = document.getElementById("addContactForm");
    form.style.display = form.style.display === "none" ? "block" : "none";
}

function submitContact(button) {
    const clientId = button.getAttribute('data-client-id');
    const contactType = document.querySelector('.contactType').value;
    const contactValue = document.querySelector('.contactValue').value;
    const contactObservation = document.querySelector('.contactObservation').value;

    const contactData = {
        tipoContato: contactType,
        valorContato: contactValue,
        observacao: contactObservation
    };

    fetch(`/clients/${clientId}/contacts`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(contactData),
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            throw new Error('Erro ao adicionar contato: ' + response.statusText);
        })
        .then(data => {
            console.log('Contato adicionado com sucesso:', data);
            alert('Contato adicionado com sucesso!');

            const contactList = document.getElementById("contactList");
            const contactItem = document.createElement("div");
            contactItem.className = "contact-item";
            contactItem.innerHTML = `
            <div>
                <p><strong>Tipo:</strong> <span>${data.tipoContato}</span></p>
                <p><strong>Valor:</strong> <span>${data.valorContato}</span></p>
                <p><strong>Observação:</strong> <span>${data.observacao}</span></p>
            </div>
            <button class="btn btn-danger" onclick="removeContact(this)">Excluir Contato</button>
        `;
            contactList.appendChild(contactItem);

            document.querySelector('.contactType').value = "";
            document.querySelector('.contactValue').value = "";
            document.querySelector('.contactObservation').value = "";

            document.getElementById("addContactForm").style.display = "none";
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Erro ao adicionar contato: ' + error.message);
        });
}

function openEditContactModal(button) {
    const contactId = button.getAttribute('data-id');
    const contactType = button.getAttribute('data-type');
    const contactValue = button.getAttribute('data-value');
    const contactObservation = button.getAttribute('data-observation');
    const clientId = button.getAttribute('data-client-id');

    document.getElementById('editContactModal').style.display = 'block';
    document.getElementById('editContactModal').setAttribute('data-id', contactId);
    document.getElementById('editContactModal').setAttribute('data-client-id', clientId);
    document.getElementById('editContactType').value = contactType;
    document.getElementById('editContactValue').value = contactValue;
    document.getElementById('editContactObservation').value = contactObservation;
}

function closeEditContactModal() {
    document.getElementById('editContactModal').style.display = 'none';
}

function updateContact(event) {
    event.preventDefault();

    const clientId = document.getElementById('editContactModal').getAttribute('data-client-id');
    const contactId = document.getElementById('editContactModal').getAttribute('data-id');

    const contactType = document.getElementById('editContactType').value;
    const contactValue = document.getElementById('editContactValue').value;
    const contactObservation = document.getElementById('editContactObservation').value;

    const contactData = {
        tipoContato: contactType,
        valorContato: contactValue,
        observacao: contactObservation
    };

    console.log('Dados do contato:', JSON.stringify(contactData));

    fetch(`/clients/${clientId}/contacts/${contactId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(contactData),
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            throw new Error('Erro ao atualizar contato: ' + response.statusText);
        })
        .then(data => {
            console.log('Contato atualizado com sucesso:', data);
            alert('Contato atualizado com sucesso!');
            closeEditContactModal();
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Erro ao atualizar contato: ' + error.message);
        });
}

function removeContact(button) {
    const contactItem = button.parentElement;
    contactItem.remove();
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

function closeEditModal() {
    document.getElementById('editClientModal').style.display = 'none';
}

let currentClientId;

function openEditModal(id) {
    currentClientId = id;
    const modal = document.getElementById('editClientModal');

    if (!modal) {
        console.error("Modal não encontrado no DOM!");
        return;
    }

    modal.style.display = 'block';
    console.log("Modal aberto com ID do cliente:", currentClientId);
}

function updateClient(event) {
    event.preventDefault();

    const nameInput = document.getElementById('editClientName');
    const cpfInput = document.getElementById('editClientCpf');
    const birthDateInput = document.getElementById('editClientBirthDate');
    const addressInput = document.getElementById('editClientAddress');

    const modal = document.getElementById('editClientModal');
    const id = modal.getAttribute('data-id');

    if (!nameInput || !cpfInput || !birthDateInput || !addressInput || !id) {
        console.error('Um ou mais elementos do formulário não foram encontrados.');
        return;
    }

    const clientData = {
        nome: nameInput.value,
        cpf: cpfInput.value,
        dataNascimento: formatarData(birthDateInput.value),
        endereco: addressInput.value,
        contato: []
    };

    const contactItems = document.querySelectorAll('.contact-item');
    contactItems.forEach(contactItem => {
        const contactType = contactItem.querySelector('.contactType');
        const contactValue = contactItem.querySelector('.contactValue');
        const contactObservation = contactItem.querySelector('.contactObservation');

        if (!contactType || !contactValue || !contactObservation) {
            console.error('Um ou mais elementos de contato não foram encontrados.');
            return;
        }

        clientData.contato.push({
            tipoContato: contactType.value,
            valorContato: contactValue.value,
            observacao: contactObservation.value
        });
    });

    /* Debug */
    console.log(`Fazendo requisição para: /clients/${id}`);
    console.log('Dados do cliente:', clientData);

    fetch(`/clients/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(clientData),
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            throw new Error('Erro ao atualizar os dados do cliente: ' + response.statusText);
        })
        .then(data => {
            console.log('Cliente atualizado com sucesso:', data);
            alert('Dados do cliente atualizados com sucesso!');
            closeEditModal();
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Erro ao atualizar os dados do cliente: ' + error.message);
        });
}

function formatarData(data) {
    const partes = data.split('-');
    return `${partes[2]}/${partes[1]}/${partes[0]}`;
}

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

function openModal() {
    document.getElementById("clientModal").style.display = "block";
}

function closeModal() {
    document.getElementById("clientModal").style.display = "none";
}

window.onclick = function (event) {
    const modal = document.getElementById("clientModal");
    if(event.target === modal) {
        closeModal();
    }
}
