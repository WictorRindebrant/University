const projectsDivs = document.getElementById('projects')

const fixedIdArray = backToArray(idArray)
const fixedNameArray = backToArray(nameArray)

// Creates project div for all the projects there are.
for (let i = 0; i < fixedIdArray.length; i++) {
  const data = {
    id: fixedIdArray[i],
    name: fixedNameArray[i]
  }
  createProjectDiv(data)
}

/**
 * Creates project div with the projects important information.
 * And make it clickable to get to the project to see its issues.
 * @param {Array} data information about the project.
 */
function createProjectDiv (data) {
  const projectButton = document.createElement('button')
  projectButton.className = 'projectButton'
  projectButton.addEventListener('click', e => {
    const projectLink = document.createElement('a')
    projectLink.href = '/git/' + data.id
    projectLink.click()
  })

  const projectName = document.createElement('h1')

  projectName.textContent = data.name

  projectButton.appendChild(projectName)
  projectsDivs.appendChild(projectButton)
}

/**
 * Fixing string that was an array back to an array
 * @param {string} stringArray string that was an array.
 * @returns {Array} the string converted back to an array.
 */
function backToArray (stringArray) {
  const fixedArray = stringArray.split(',')
  return fixedArray
}
