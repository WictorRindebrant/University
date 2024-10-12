const issueDivs = document.getElementById('issues')

// converts strings that was arrays back to arrays.
const fixedIdArray = backToArray(idArray)
const fixedNameArray = backToArray(titleArray)
const fixedDescriptionArray = backToArray(descriptionArray)
const fixedStateArray = backToArray(stateArray)
const fixedCreatedArray = backToArray(createdArray)
const fixedUpdatedArray = backToArray(updatedArray)
const fixedLabelsArray = backToArray(labelsArray)
const fixedMilestoneArray = backToArray(milestoneArray)
const fixedAuthorArray = backToArray(authorArray)

/**
 * Checks if there is an issue and if not shows that with a message.
 * Else creates the issue div with all of the issues important information.
 */
if (fixedIdArray[0] === '') {
  const projectIssueText = document.getElementById('projectIssueText')
  projectIssueText.textContent = 'There is no ISSUES for this project'
} else {
  for (let i = 0; i < fixedIdArray.length; i++) {
    const data = {
      id: fixedIdArray[i],
      name: fixedNameArray[i],
      description: fixedDescriptionArray[i],
      state: fixedStateArray[i],
      created: fixedCreatedArray[i],
      updated: fixedUpdatedArray[i],
      label: fixedLabelsArray[i],
      milestone: fixedMilestoneArray[i],
      author: fixedAuthorArray[i]
    }
    createIssueDiv(data)
  }
}

/**
 * Creates the issue div and fills it with information from the issue.
 * @param {Array} data information about that issue
 */
function createIssueDiv (data) {
  const issueDiv = document.createElement('div')
  if (data.state === 'opened') {
    issueDiv.className = 'issueDivOpen'
  } else {
    issueDiv.className = 'issueDivClose'
  }

  const issueTitle = document.createElement('h2')
  issueTitle.className = 'issueTitle issueText'
  issueTitle.textContent = 'Title: ' + data.name

  const issueId = document.createElement('h3')
  issueId.className = 'issueId issueText'
  issueId.textContent = 'ID: ' + data.id

  const issueDescription = document.createElement('h3')
  issueDescription.className = 'issueDescription issueText'
  issueDescription.textContent = 'Description: ' + data.description

  const issueState = document.createElement('h3')
  issueState.className = 'issueState issueText'
  issueState.textContent = 'State: ' + data.state

  const issueCreated = document.createElement('h3')
  issueCreated.className = 'issueCreated issueText'
  issueCreated.textContent = 'Created: ' + data.created

  const issueUpdated = document.createElement('h3')
  issueUpdated.className = 'issueUpdated issueText'
  issueUpdated.textContent = 'Updated: ' + data.updated

  const issueLabel = document.createElement('h3')
  issueLabel.className = 'issueLabel issueText'
  issueLabel.textContent = 'Labels: ' + data.label

  const issueMilestone = document.createElement('h3')
  issueMilestone.className = 'issueMilestone issueText'
  issueMilestone.textContent = 'Milestones: ' + data.milestone

  const issueAuthor = document.createElement('h3')
  issueAuthor.className = 'issueAuthor issueText'
  issueAuthor.textContent = 'Author: ' + data.author

  issueDiv.appendChild(issueTitle)
  issueDiv.appendChild(issueId)
  issueDiv.appendChild(issueDescription)
  issueDiv.appendChild(issueState)
  issueDiv.appendChild(issueLabel)
  issueDiv.appendChild(issueMilestone)
  issueDiv.appendChild(issueAuthor)
  issueDivs.appendChild(issueDiv)
  issueDiv.appendChild(issueCreated)
  issueDiv.appendChild(issueUpdated)
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
