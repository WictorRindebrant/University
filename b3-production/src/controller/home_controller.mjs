export const homeController = {}

// Get home page.
homeController.home = async (req, res) => {
  const flashMessage = req.session.flashMessage ?? null
  req.session.flashMessage = null

  const data = {
    flashMessage
  }

  res.render('home', data)
}
