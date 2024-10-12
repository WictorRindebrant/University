import * as dotenv from 'dotenv'

dotenv.config()
export const gitModel = {}

// Gets all the projects from a group of projects from gitLab.
gitModel.getProjects = async () => {
  const response = await fetch(`https://gitlab.lnu.se/api/v4/groups/54854/projects?access_token=${process.env.GITLAB_GROUP_B3_TOKEN}`)
  const data = await response.json()
  // console.log(data)

  return data
}

// Gets a specific project from its project id from gitLab.
gitModel.getProject = async (projectId) => {
  const response = await fetch(`https://gitlab.lnu.se/api/v4/projects/${projectId}?access_token=${process.env.GITLAB_GROUP_B3_TOKEN}`)
  const data = await response.json()
  // console.log(data)

  return data
}

// Gets all the issues from a specific project from its project id from gitLab.
gitModel.getIssues = async (projectId) => {
  const response = await fetch(`https://gitlab.lnu.se/api/v4/projects/${projectId}/issues?access_token=${process.env.GITLAB_GROUP_B3_TOKEN}`)
  const data = await response.json()
  // console.log(data)

  return data
}
