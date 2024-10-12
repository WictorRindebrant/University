/* Wictor Rindebrant */
'use strict'

const dragzone = document.getElementById('desktop')
let lastmovedwindow
/**
 * Prevents the window to go outside the dragzone.
 * @param {HTMLElement} dragwindow The draggable window that is created.
 */
export function setElements (dragwindow) {
  dragzone.addEventListener('dragover', (event) => {
    event.preventDefault()
  })

  /* Takes all the vaules from where the window started to be dragged from. */
  dragwindow.addEventListener('dragstart', (event) => {
    const style = window.getComputedStyle(event.target, null)
    const startX = parseInt(style.getPropertyValue('left'), 10) - event.clientX
    const startY = parseInt(style.getPropertyValue('top'), 10) - event.clientY
    const start = {
      posX: startX,
      posY: startY
    }
    event.dataTransfer.setData('application/json', JSON.stringify(start))

    /* Solves problem so that dragzone event only moves 1 window and now all. */
    lastmovedwindow = dragwindow
  })
  /* Sets the new position for the window when dropped. */
  dragzone.addEventListener('drop', (event) => {
    const start = JSON.parse(event.dataTransfer.getData('application/json'))
    const dropX = event.clientX
    const dropY = event.clientY
    lastmovedwindow.style.left = (dropX + start.posX) + 'px'
    lastmovedwindow.style.top = (dropY + start.posY) + 'px'
  })
}
