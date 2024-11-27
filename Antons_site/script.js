let gamesDatabase = []

fetch('games.json')
	.then(response => response.json())
	.then(data => {
		gamesDatabase = data
	})
	.catch(error => {
		console.error('Ошибка загрузки данных: ', error)
	})

function findGames() {
	const playerCount = Number(document.getElementById('playerCount').value)
	const gameType = document.getElementById('gameType').value

	const gamesList = document.getElementById('gamesList')
	gamesList.innerHTML = ''

	const filteredGames = gamesDatabase.filter(
		game => game.players.includes(playerCount) && game.type === gameType
	)

	displayGames(filteredGames)
}

function searchGames() {
	const query = document.getElementById('searchBar').value.toLowerCase()
	const filteredGames = gamesDatabase.filter(game =>
		game.name.toLowerCase().includes(query)
	)

	displayGames(filteredGames)
}

function displayGames(games) {
	const gamesList = document.getElementById('gamesList')
	gamesList.innerHTML = ''

	if (games.length === 0) {
		gamesList.innerHTML =
			'<li>Извините, игры не найдены по выбранным параметрам.</li>'
	} else {
		games.forEach(game => {
			const li = document.createElement('li')
			li.classList.add('game-item')
			li.textContent = game.name
			li.onclick = () => showGameDetails(game)
			gamesList.appendChild(li)
		})
	}
}

function showGameDetails(game) {
	const modal = document.getElementById('gameModal')
	document.getElementById('gameTitle').textContent = game.name
	document.getElementById('gameDescription').textContent = game.description
	modal.style.display = 'flex'
}

function closeModal() {
	document.getElementById('gameModal').style.display = 'none'
}

function login() {
	alert('Функция входа пока не реализована.')
}
