import express from 'express'
import { homeController } from '../controller/home_controller.mjs'
import { gitController } from '../controller/git_controller.mjs'
import { leftoverController } from '../controller/leftover_controller.mjs'

const router = express.Router()
export default router

router.get('/', (req, res) => {
  res.redirect(301, 'home')
})
router.get('/error', (req, res) => {
  process.exit(1)
})
router.get('/home', homeController.home)
router.get('/git', gitController.git)
router.get('/git/:projectId', gitController.gitProject)
router.post('/webhook', gitController.getData)
router.get('*', leftoverController.leftoverPath)
