import mongoose from 'mongoose'
import { Schema } from './database_model.mjs'

const snippetSchema = new Schema({
  title: String,
  code: String,
  user: String
})

export const Snippet = mongoose.model('Snippet', snippetSchema)
export const snippetModel = {}

// Gets all the snippets from the snippet database.
snippetModel.listAll = async (data) => {
  const snippets = await Snippet.find()
  return snippets
}

// Adds a snippet to the snippet database.
snippetModel.add = async (snippet) => {
  const newSnippet = new Snippet({
    title: snippet.snippetTitle,
    code: snippet.snippetCode,
    user: snippet.user.username,
    editMode: snippet.editMode
  })

  newSnippet.save()
  return newSnippet
}

// Deletes a snippet from the snippet database.
snippetModel.delete = async (snippetId) => {
  const snippedFound = await Snippet.findOne({ _id: snippetId })
  await Snippet.deleteOne(snippedFound)
}

// Saves a snippet that was edited to the database.
snippetModel.save = async (snippetId, snippetCode) => {
  await Snippet.updateOne({ _id: snippetId }, { code: snippetCode })
}

// snippetModel.reset = async () => {
//   await Snippet.deleteMany()
// }
