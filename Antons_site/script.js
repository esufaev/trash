let gamesDatabase = []
let authMode = 'login'

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
	openAuthModal()
}

function openAuthModal() {
	document.getElementById('authModal').style.display = 'flex'
}

function closeAuthModal() {
	document.getElementById('authModal').style.display = 'none'
}

function toggleAuthMode() {
	const authTitle = document.getElementById('authTitle')
	const authSubmitButton = document.getElementById('authSubmitButton')
	const toggleAuth = document.getElementById('toggleAuth')

	if (authMode === 'login') {
		authMode = 'register'
		authTitle.textContent = 'Регистрация'
		authSubmitButton.textContent = 'Зарегистрироваться'
		toggleAuth.textContent = 'Уже есть аккаунт? Войти'
	} else {
		authMode = 'login'
		authTitle.textContent = 'Вход'
		authSubmitButton.textContent = 'Войти'
		toggleAuth.textContent = 'Нет аккаунта? Зарегистрируйтесь'
	}
}

function validateUsername(username) {
	const usernameRegex = /^[a-zA-Z0-9_]{3,15}$/
	if (!usernameRegex.test(username)) {
		alert(
			'Никнейм должен быть длиной от 3 до 15 символов и содержать только буквы, цифры и подчёркивания.'
		)
		return false
	}
	return true
}

function validatePassword(password) {
	const passwordRegex = /^(?=.*[a-zA-Z])(?=.*\d).{6,20}$/
	if (!passwordRegex.test(password)) {
		alert(
			'Пароль должен быть длиной от 6 до 20 символов и содержать хотя бы одну букву и одну цифру.'
		)
		return false
	}
	return true
}

function submitAuthForm(event) {
	event.preventDefault()
	const username = document.getElementById('username').value.trim()
	const password = document.getElementById('password').value.trim()

	if (!validateUsername(username) || !validatePassword(password)) {
		return
	}

	if (authMode === 'register') {
		registerUser(username, password)
	} else {
		loginUser(username, password)
	}
}

function registerUser(username, password) {
	const users = JSON.parse(localStorage.getItem('users')) || {}

	if (users[username]) {
		alert('Пользователь с таким именем уже существует!')
		return
	}

	users[username] = password
	localStorage.setItem('users', JSON.stringify(users))
	alert('Регистрация прошла успешно! Войдите в систему.')
	toggleAuthMode()
}

function loginUser(username, password) {
	const users = JSON.parse(localStorage.getItem('users')) || {}

	if (!users[username]) {
		alert('Пользователь не найден!')
		return
	}

	if (users[username] !== password) {
		alert('Неправильный пароль!')
		return
	}

	alert(`Добро пожаловать, ${username}!`)
	closeAuthModal()
	document.querySelector('.login-button').textContent = `Привет, ${username}`
}
