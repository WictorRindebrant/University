export const leftoverController = {}

// Handles error status and messages for paths that does not exist.
leftoverController.leftoverPath = async (req, res) => {
  const errorData = leftoverController.createErrorData('404', 'Path does not exist: ' + req.url)
  res.status(404).render('error', errorData)
}

// Creates an array with error data that is being returned.
leftoverController.createErrorData = (statusCode, errorMessage) => {
  const errorData = {
    statusCode,
    errorMessage
  }
  return errorData
}
