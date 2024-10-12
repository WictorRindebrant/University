const docFlashMessage = document.getElementById('flashMessage')

// if the flashMessage is empty then dont show it.
if (flashMessage === '') {
  docFlashMessage.style.display = 'none'
}

/**
 * if the flshMessage gets a new message then show it.
 * @param {string} message message that will be shown in flashMessage.
 */
export function newMessage (message) {
  docFlashMessage.style.display = 'block'
  docFlashMessage.textContent = message
}
