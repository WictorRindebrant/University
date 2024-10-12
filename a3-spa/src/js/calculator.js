/* Wictor Rindebrant */
'use strict'

import calcImgUrl from '../img/toolbar/calculator.png'

import { setElements } from '../js/modules/draggable.js'
import { layerOnTop, newWindow } from './modules/window.js'

const parentElement = document.getElementById('desktop')
const calculatorBtn = document.getElementById('calculatorBtn')

calculatorBtn.addEventListener('click', e => {
  const dragWindow = createWindow()
  const calcWindow = createCalcElements(dragWindow)
  const calcBtn = calcWindow.children[7]
  calcBtn.addEventListener('click', e => {
    const value1 = calcWindow.children[4]
    const value2 = calcWindow.children[5]
    const operator = calcWindow.children[6]
    const answer = calcWindow.children[8]
    if (operator.value === 'x') {
      answer.textContent = '= ' + (parseFloat(value1.value) * parseFloat(value2.value))
    } else if (operator.value === '*') {
      answer.textContent = '= ' + (parseFloat(value1.value) * parseFloat(value2.value))
    } else if (operator.value === '/') {
      answer.textContent = '= ' + (parseFloat(value1.value) / parseFloat(value2.value))
    } else if (operator.value === '-') {
      answer.textContent = '= ' + (parseFloat(value1.value) - parseFloat(value2.value))
    } else if (operator.value === '+') {
      answer.textContent = '= ' + (parseFloat(value1.value) + parseFloat(value2.value))
    }
  })
})

/**
 * Creates the calculator window and elements and adds it to the windwo.
 * @param {HTMLElement} window the window is the main window for the calculator app.
 * @returns {HTMLElement} the calculation window with all the calc elements.
 */
function createCalcElements (window) {
  const calcWindow = document.createElement('div')
  calcWindow.id = 'calcWindow' + idnumb
  calcWindow.className = 'calcWindow'

  const calcText = document.createElement('h1')
  calcText.id = 'calcText'
  calcText.className = 'calcText'
  calcText.textContent = 'Calculator'

  const calcInputText1 = document.createElement('h3')
  calcInputText1.className = 'calcInputText1'
  calcInputText1.textContent = 'calcInputText1'
  calcInputText1.textContent = 'Value1:'

  const calcInputText2 = document.createElement('h3')
  calcInputText2.className = 'calcInputText2'
  calcInputText2.textContent = 'calcInputText2'
  calcInputText2.textContent = 'Value2:'

  const calcInputText3 = document.createElement('h3')
  calcInputText3.className = 'calcInputText3'
  calcInputText3.textContent = 'calcInputText3'
  calcInputText3.textContent = 'Operator: '

  const calcInput1 = document.createElement('input')
  calcInput1.className = 'calcInput1'
  calcInput1.id = 'calcInput1'

  const calcInput2 = document.createElement('input')
  calcInput2.className = 'calcInput2'
  calcInput2.id = 'calcInput2'

  const calcInput3 = document.createElement('input')
  calcInput3.className = 'calcInput3'
  calcInput3.id = 'calcInput3'

  const calcBtn = document.createElement('button')
  calcBtn.className = 'calcBtn'
  calcBtn.id = 'calcBtn'
  calcBtn.textContent = 'Calculate'

  const calcAnswer = document.createElement('h1')
  calcAnswer.id = 'calcAnswer'
  calcAnswer.className = 'calcAnswer'

  calcWindow.appendChild(calcText)
  calcWindow.appendChild(calcInputText1)
  calcWindow.appendChild(calcInputText2)
  calcWindow.appendChild(calcInputText3)
  calcWindow.appendChild(calcInput1)
  calcWindow.appendChild(calcInput2)
  calcWindow.appendChild(calcInput3)
  calcWindow.appendChild(calcBtn)
  calcWindow.appendChild(calcAnswer)
  window.appendChild(calcWindow)
  return calcWindow
}

let idnumb = 0
/**
 * This is the main window for the calulator application.
 * @returns {HTMLElement} returns the calulator application window.
 */
function createWindow () {
  const dragWindow = document.createElement('div')
  dragWindow.id = 'dragwindow' + idnumb
  dragWindow.className = 'dragwindow'
  dragWindow.setAttribute('draggable', 'true')
  dragWindow.addEventListener('mousedown', e => {
    layerOnTop(dragWindow)
  })

  const dragHeader = document.createElement('header')
  dragHeader.id = 'dragheader' + idnumb
  dragHeader.className = 'dragheader'
  dragHeader.setAttribute('draggable', 'false')

  const img = document.createElement('img')
  img.src = calcImgUrl
  img.className = 'dragheadimg'

  const h3 = document.createElement('h3')
  h3.className = 'dragh3'
  h3.textContent = 'Calculator'

  const closebutton = document.createElement('button')
  closebutton.className = 'dragclosebtn'
  closebutton.textContent = 'X'
  closebutton.addEventListener('click', e => {
    parentElement.removeChild(dragWindow)
  })

  dragHeader.appendChild(img)
  dragHeader.appendChild(h3)
  dragHeader.appendChild(closebutton)
  dragWindow.appendChild(dragHeader)

  parentElement.appendChild(dragWindow)
  newWindow(dragWindow)
  layerOnTop(dragWindow)
  setElements(dragWindow)
  idnumb++
  return dragWindow
}
