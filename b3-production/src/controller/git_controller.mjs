import { gitModel } from './../model/git_model.mjs'
import wsServer from '../websocket.mjs'
import * as dotenv from 'dotenv'

dotenv.config()
export const gitController = {}

// Get git page with all the projects that belongs to that git group.
gitController.git = async (req, res) => {
  const flashMessage = req.session.flashMessage ?? null
  req.session.flashMessage = null

  const response = await gitModel.getProjects()

  const idArray = []
  const nameArray = []
  response.forEach(element => {
    idArray.push(element.id)
    nameArray.push(element.name)
  })

  const data = {
    flashMessage,
    idArray,
    nameArray
  }

  res.render('git', data)
}

// Get project page with the specific project and shows all of its issues.
gitController.gitProject = async (req, res) => {
  const flashMessage = req.session.flashMessage ?? null
  const projectId = req.params.projectId
  const projectResponse = await gitModel.getProject(projectId)
  const issueResponse = await gitModel.getIssues(projectId)

  const projectName = projectResponse.name
  const projectID = projectResponse.id

  const titleArray = []
  const idArray = []
  const descriptionArray = []
  const stateArray = []
  const createdArray = []
  const updatedArray = []
  const labelsArray = []
  const milestoneArray = []
  const authorArray = []
  req.session.flashMessage = null

  issueResponse.forEach(element => {
    idArray.push(element.id)
    titleArray.push(element.title)
    descriptionArray.push(element.description)
    stateArray.push(element.state)
    createdArray.push(element.created_at)
    updatedArray.push(element.updated_at)
    labelsArray.push(arrayToString(element.labels))
    milestoneArray.push(arrayToString(element.milestone))
    authorArray.push(element.author.username)
  })

  /**
   * Converts an array to a string.
   * @param {Array} multipleArray An array with 1 or more objects.
   * @returns {string} string with the elements from the array seperated with '+'.
   */
  function arrayToString (multipleArray) {
    let fixedString = ''
    if (multipleArray !== null) {
      multipleArray.forEach(element => {
        fixedString += ' + ' + element
      })
      fixedString = fixedString.substring(2)
    }
    return fixedString
  }

  const data = {
    flashMessage,
    projectName,
    projectID,
    titleArray,
    idArray,
    descriptionArray,
    stateArray,
    createdArray,
    updatedArray,
    labelsArray,
    milestoneArray,
    authorArray
  }

  res.render('project', data)
}

// POST from gitLab when something from gitLab is updated that belongs to our gitLab Token.
gitController.getData = async (req, res) => {
  if (req.headers['x-gitlab-token'] === process.env.X_GITLAB_TOKEN) {
    const gitLabEvent = req.body.object_kind
    const gitLabProject = req.body.project.name

    res.status(200).send('OK')
    wsServer.broadcastExceptSelf(null, 'NEW Event (' + gitLabEvent + ') on the project (' + gitLabProject + ')!')
  } else {
    res.status(403).send('Permission Denied!')
  }
}
