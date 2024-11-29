let users = []
let games = []
let currentUser = null
let favorites = []
let showFavoritesOnly = false

const ads = [
	{
		image: 'ads/cyberpunk2077.jpg',
		link: 'https://store.steampowered.com/app/1091500/Cyberpunk_2077/?l=russian',
	},
	{
		image: 'ads/war_thunder.jpg',
		link: 'https://store.steampowered.com/app/236390/War_Thunder/',
	},
	{
		image: 'ads/bg3.jpg',
		link: 'https://store.steampowered.com/app/1086940/Baldurs_Gate_3/',
	},
]

document.addEventListener('DOMContentLoaded', () => {
	fetch('users.json')
		.then(response => response.json())
		.then(data => {
			users = data
		})
		.catch(() => showAlert('Ошибка загрузки пользователей', true))

	fetch('games.json')
		.then(response => response.json())
		.then(data => {
			games = data
			recommendGames()
			renderGames()
		})
		.catch(() => showAlert('Ошибка загрузки игр', true))
})

function toggleAuthModal(show = null) {
	const authModal = document.getElementById('authModal')
	authModal.style.display =
		show === null
			? authModal.style.display === 'block'
				? 'none'
				: 'block'
			: show
			? 'block'
			: 'none'
}

function toggleForm() {
	const loginForm = document.getElementById('loginForm')
	const registerForm = document.getElementById('registerForm')
	const modalTitle = document.getElementById('modalTitle')

	if (loginForm.style.display === 'none') {
		loginForm.style.display = 'block'
		registerForm.style.display = 'none'
		modalTitle.textContent = 'Войти'
	} else {
		loginForm.style.display = 'none'
		registerForm.style.display = 'block'
		modalTitle.textContent = 'Зарегистрироваться'
	}
}

function showAlert(message, isError = false) {
	const alertContainer = document.getElementById('alertContainer')
	const alertDiv = document.createElement('div')
	alertDiv.className = 'alert' + (isError ? '' : ' success')
	alertDiv.textContent = message

	alertContainer.appendChild(alertDiv)

	setTimeout(() => alertDiv.classList.add('fade'), 3000) 
	setTimeout(() => alertDiv.remove(), 4000)
}


function displayUser(username) {
	currentUser = username
	document.getElementById('authButton').style.display = 'none'
	document.getElementById('userDisplay').style.display = 'block'
	document.getElementById(
		'usernameDisplay'
	).textContent = `Привет, ${username}!`
	document.getElementById('logoutButton').style.display = 'inline-block'

	recommendGames()
	renderGames()
}

function login() {
	const username = document.getElementById('username').value.trim()
	const password = document.getElementById('password').value.trim()
	if (!username || !password) return showAlert('Заполните все поля', true)

	const user = users.find(
		u => u.username === username && u.password === password
	)
	if (user) {
		showAlert(`Добро пожаловать, ${username}!`)
		displayUser(username)
		toggleAuthModal(false)
	} else {
		showAlert('Неверное имя пользователя или пароль', true)
	}
}

function register() {
	const username = document.getElementById('regUsername').value.trim()
	const password = document.getElementById('regPassword').value.trim()
	const confirmPassword = document
		.getElementById('confirmPassword')
		.value.trim()
	if (!username || !password || !confirmPassword)
		return showAlert('Заполните все поля', true)
	if (password !== confirmPassword)
		return showAlert('Пароли не совпадают', true)
	if (password.length < 6)
		return showAlert('Пароль должен быть не менее 6 символов', true)

	if (users.some(u => u.username === username)) {
		showAlert('Пользователь с таким именем уже существует', true)
	} else {
		users.push({ username, password })
		showAlert(`Пользователь ${username} зарегистрирован`)
		displayUser(username)
		toggleAuthModal(false)
	}
}

function recommendGames() {
	const genre = document.getElementById('genre').value.toLowerCase()
	const platform = document.getElementById('platform').value.toLowerCase()
	const rating = document.getElementById('rating').value.toLowerCase()

	let filteredGames = games.filter(game => {
		const genreMatches = genre === 'all' || game.genre.toLowerCase() === genre
		const platformMatches =
			platform === 'all' || game.platform.toLowerCase() === platform
		const ratingMatches =
			rating === 'all' || game.rating.toLowerCase() === rating
		return genreMatches && platformMatches && ratingMatches
	})

	if (showFavoritesOnly) {
		filteredGames = filteredGames.filter(game => favorites.includes(game.title))
	}

	const gameList = document.getElementById('gameList')
	if (filteredGames.length > 0) {
		gameList.innerHTML = ''
		filteredGames.forEach(game => {
			const isFavorite = favorites.includes(game.title) 
			const gameItem = document.createElement('div')
			gameItem.classList.add('game')
			gameItem.innerHTML = `
				<h3>${game.title}</h3>
				<p>Жанр: ${game.genre}</p>
				<p>Платформа: ${game.platform}</p>
				<p>Рейтинг: ${game.rating}</p>
				<div class="favorite-container">
					<button class="favorite-button" onclick="toggleFavorite('${game.title}')">
						${isFavorite ? '&#9733;' : '&#9734;'} <!-- Полная или пустая звезда -->
					</button>
				</div>
			`
			gameItem.onclick = event => {
				if (!event.target.classList.contains('favorite-button')) {
					displayGameDetails(game)
				}
			}
			gameList.appendChild(gameItem)
		})
	} else {
		gameList.innerHTML = '<p>Нет подходящих игр</p>'
	}
}


function logout() {
	currentUser = null
	document.getElementById('authButton').style.display = 'inline-block'
	document.getElementById('userDisplay').style.display = 'none'
	document.getElementById('logoutButton').style.display = 'none'
}

function displayGameDetails(game) {
	document.getElementById('gameTitle').textContent = game.title
	document.getElementById('gameImage').src = game.imagePath
	document.getElementById('gameImage').alt = game.title
	document.getElementById('gameDescription').textContent = game.description
	document.getElementById('gameGenre').textContent = `Жанр: ${game.genre}`
	document.getElementById(
		'gamePlatform'
	).textContent = `Платформа: ${game.platform}`
	document.getElementById('gameRating').textContent = `Рейтинг: ${game.rating}`
	document.getElementById('gameTrailer').src = game.trailerPath
	toggleGameModal(true)
}

function renderGames() {
	const gameList = document.getElementById('gameList')
	gameList.innerHTML = ''

	games.forEach(game => {
		const gameItem = document.createElement('div')
		gameItem.classList.add('game')
		gameItem.innerHTML = `
			<h3>${game.title}</h3>
			<p>Жанр: ${game.genre}</p>
			<p>Платформа: ${game.platform}</p>
			<p>Рейтинг: ${game.rating}</p>
			<div class="favorite-container">
				<button class="favorite-button" onclick="toggleFavorite('${game.title}')">
					&#9734; <!-- Пустая звезда -->
				</button>
			</div>
		`
		gameItem.onclick = event => {
			if (!event.target.classList.contains('favorite-button')) {
				displayGameDetails(game)
			}
		}
		gameList.appendChild(gameItem)
	})
}

window.onclick = function (event) {
	const modal = document.getElementById('gameModal')
	if (event.target === modal) {
		toggleGameModal(false)
	}
}

function toggleGameModal(show) {
	const modal = document.getElementById('gameModal')
	if (!show) {
		document.getElementById('gameTrailer').src = ''
	}
	modal.style.display = show ? 'block' : 'none'
}

let currentAdIndex = 0

function showAd() {
	const adContainer = document.getElementById('adContainer')
	const adImage = document.getElementById('adImage')
	const adLink = document.getElementById('adLink')

	const ad = ads[currentAdIndex]
	adImage.src = ad.image
	adLink.href = ad.link

	adContainer.style.display = 'block'

	setTimeout(() => {
		currentAdIndex = (currentAdIndex + 1) % ads.length
		showAd()
	}, 10000)
}

function toggleAd(show) {
	const adContainer = document.getElementById('adContainer')
	adContainer.style.display = show ? 'block' : 'none'
}

document.addEventListener('DOMContentLoaded', showAd)

function startBouncing() {
	const adContainer = document.getElementById('adContainer')

	adContainer.classList.add('bouncing')

	setTimeout(() => adContainer.classList.remove('bouncing'), 1000)

	setTimeout(startBouncing, 15000)
}

document.addEventListener('DOMContentLoaded', () => {
	showAd()
	startBouncing()
})

function toggleFavorite(gameTitle) {
	const index = favorites.indexOf(gameTitle)
	const buttons = document.querySelectorAll('.favorite-button')

	if (index === -1) {
		favorites.push(gameTitle)
		showAlert(`Игра "${gameTitle}" добавлена в избранное`)
	} else {
		favorites.splice(index, 1)
		showAlert(`Игра "${gameTitle}" удалена из избранного`)
	}

	games.forEach((game, i) => {
		if (favorites.includes(game.title)) {
			buttons[i].innerHTML = '&#9733;' 
		} else {
			buttons[i].innerHTML = '&#9734;' 
		}
	})
}

function toggleFavoritesOnly() {
	showFavoritesOnly = !showFavoritesOnly
	const button = document.querySelector('.favorite-toggle')
	button.textContent = showFavoritesOnly ? 'Показать все' : 'Избранное'
	recommendGames()
}

function searchGames() {
	const query = document
		.getElementById('searchInput')
		.value.trim()
		.toLowerCase()
	const gameList = document.getElementById('gameList')

	// Фильтрация игр по названию
	const filteredGames = games.filter(game =>
		game.title.toLowerCase().includes(query)
	)

	if (filteredGames.length > 0) {
		gameList.innerHTML = ''
		filteredGames.forEach(game => {
			const isFavorite = favorites.includes(game.title)
			const gameItem = document.createElement('div')
			gameItem.classList.add('game')
			gameItem.innerHTML = `
				<h3>${game.title}</h3>
				<p>Жанр: ${game.genre}</p>
				<p>Платформа: ${game.platform}</p>
				<p>Рейтинг: ${game.rating}</p>
				<div class="favorite-container">
					<button class="favorite-button" onclick="toggleFavorite('${game.title}')">
						${isFavorite ? '&#9733;' : '&#9734;'} <!-- Полная или пустая звезда -->
					</button>
				</div>
			`
			gameItem.onclick = event => {
				if (!event.target.classList.contains('favorite-button')) {
					displayGameDetails(game)
				}
			}
			gameList.appendChild(gameItem)
		})
	} else {
		gameList.innerHTML = '<p>Нет подходящих игр</p>'
	}
}
