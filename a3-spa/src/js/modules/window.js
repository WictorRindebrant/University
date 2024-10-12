/* Wictor Rindebrant */
'use strict'

let z = 0
/**
 * Changes the ZIndex to make the window appear above others.
 * @param {HTMLElement} window The window to change zIndex of.
 */
export function layerOnTop (window) {
  window.style.zIndex = z + 1
  z++
}

let offsetTop = 0
let offsetLeft = 0
/**
 * Function that will offset the new window a little, so it does not stack with other windows.
 * @param {HTMLElement} window The new window that is created.
 */
export function newWindow (window) {
  offsetTop += 2
  offsetLeft += 1
  if (offsetTop > 95) {
    offsetTop = 2
  }
  if (offsetLeft > 85) {
    offsetLeft = 1
  }
  window.style.position = 'absolute'
  window.style.top = offsetTop + '%'
  window.style.left = offsetLeft + '%'
}
