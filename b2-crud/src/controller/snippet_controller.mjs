import { snippetModel } from './../model/snippet_model.mjs'

export const snippetController = {}

// Get snippet page values.
snippetController.snippets = async (req, res) => {
  const snippets = await snippetModel.listAll()
  const flashMessage = req.session.flashMessage ?? null
  const flashMessageStatus = req.session.flashMessageStatus ?? null
  const user = req.session.user ?? null
  const editMode = req.session.editMode ?? null

  req.session.editMode = false
  req.session.flashMessage = null
  req.session.flashMessageStatus = null

  const data = {
    snippets,
    flashMessage,
    flashMessageStatus,
    user,
    editMode
  }

  res.render('snippets', data)
}

// Get new snippet page values.
snippetController.newsnippet = async (req, res) => {
  const snippetTitle = req.session.snippetTitle ?? null
  const snippetCode = req.session.snippetCode ?? null
  const flashMessage = req.session.flashMessage ?? null
  const flashMessageStatus = req.session.flashMessageStatus ?? null
  const user = req.session.user ?? null

  req.session.snippetTitle = null
  req.session.snippetCode = null
  req.session.flashMessage = null
  req.session.flashMessageStatus = null

  const data = {
    snippets: await snippetModel.listAll(),
    snippetTitle,
    snippetCode,
    flashMessage,
    flashMessageStatus,
    user
  }

  if (user) {
    res.render('newsnippet', data)
  } else {
    const errorData = snippetController.createErrorData('403', 'Login to create a snippet')
    res.status(403).render('error', errorData)
  }
}

// Post new snippet.
snippetController.addsnippet = async (req, res) => {
  const snippetTitle = req.body.snippetTitle
  const snippetCode = req.body.snippetCode
  const user = req.session.user

  const data = {
    snippetTitle,
    snippetCode,
    user
  }

  if (!snippetTitle) {
    req.session.flashMessage = ('You need a titel for you snippet!')
    req.session.flashMessageStatus = 'red'
    res.status(400).redirect('/snippets/newsnippet')
  } else if (!snippetCode) {
    req.session.flashMessage = ('You need some code for you snippet!')
    req.session.flashMessageStatus = 'red'
    res.status(400).redirect('/snippets/newsnippet')
  } else if (user) {
    await snippetModel.add(data)
    res.redirect('/snippets')
  } else {
    req.session.flashMessage = ('You need to be logged in to create a snippet!')
    req.session.flashMessageStatus = 'red'
    res.status(403).redirect('/snippets')
  }
}

// Deleting a snippet.
snippetController.deletesnippet = async (req, res) => {
  snippetModel.delete(req.body.snippetId)
  res.redirect('/snippets')
}

// Saving an edited snippet.
snippetController.savesnippet = async (req, res) => {
  snippetModel.save(req.body.snippetId, req.body.snippetCode)
  res.redirect('/snippets')
}

// Creates an array with error data that is being returned.
snippetController.createErrorData = (statusCode, errorMessage) => {
  const errorData = {
    statusCode,
    errorMessage
  }
  return errorData
}

// Resets the DB.
// snippetController.reset = async (req, res) => {
//   await snippetModel.reset()
//   res.redirect('/snippets')
// }
