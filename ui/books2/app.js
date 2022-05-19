const API = 'http://127.0.0.1:8001/api/books';

// recuperer les donnees
function fechtData() {
    const tableBody = document.getElementById('booksBody')
    fetch(API).then(response => response.json()).then(data => {
        let html = ``;
        data.map(book => {
            html += `<tr>
            <td>${book.id}</td>
            <td>${book.title}</td>
            <td>${book.author}</td>
        </tr>`
        })
        tableBody.innerHTML = html
    });
}

// Cabler le refresh des donnees sur le boutton
document.getElementById('refresh').addEventListener('click', fechtData)
// Lancer le rechargement des donnes
fechtData()


// Ajout d'un livre

document.getElementById('form').addEventListener('submit', postBook)

function postBook(e) {
    e.preventDefault()
    const author = document.getElementById('author').value;
    const title = document.getElementById('title').value;
    console.log(author, title)

    const data = { author: author, title: title }
    fetch(API, {
        method: 'POST', body: JSON.stringify(data), headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    }).then(() => {
        author.value = ''
        title.value = ''
        fechtData()
    })
}