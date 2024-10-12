import mongoose from 'mongoose'
import * as dotenv from 'dotenv'

dotenv.config()
mongoose.Promise = global.Promise

export const model = {}

// Connection to the MongoDB.
mongoose.set('strictQuery', false)
mongoose.connect(process.env.MONGO_URI, {
  serverSelectionTimeoutMS: 3000
})

console.log('Connected to the Database!')

// Database schema
export const Schema = mongoose.Schema
