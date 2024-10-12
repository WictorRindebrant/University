import express from 'express'
import { snippetController } from '../controller/snippet_controller.mjs'
import { userController } from '../controller/user_controller.mjs'
import { leftoverController } from '../controller/leftover_controller.mjs'

const router = express.Router()
export default router

router.get('/', (req, res) => {
  res.redirect(301, 'http://localhost:3000/snippets')
})
router.get('/snippets', snippetController.snippets)
router.get('/snippets/newsnippet', snippetController.newsnippet)
router.post('/snippets/newsnippet', snippetController.addsnippet)
router.post('/snippets/delete', snippetController.deletesnippet)
router.post('/snippets/save', snippetController.savesnippet)

router.get('/login', userController.login)
router.post('/login', userController.loginProcess)
router.get('/logout', userController.logout)

router.get('/register', userController.register)
router.post('/register', userController.newuser)

// router.get('/reset', userController.reset, snippetController.reset)
router.get('*', leftoverController.leftoverPath)
