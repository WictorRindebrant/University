import mongoose from 'mongoose'
import { Schema } from './database_model.mjs'
import bcrypt from 'bcrypt'

const userSchema = new Schema({
  username: String,
  password: String
})

export const User = mongoose.model('User', userSchema)
export const userModel = {}

// Gets all the users from the database.
userModel.listAll = async () => {
  const users = await User.find()
  return users
}

// Adds a user to the user database.
userModel.add = async (user) => {
  // Fix check for if user already exist.
  const userFound = await User.findOne({ username: user.username })
  const hashedPassword = await userModel.passwordHash(user.password)

  if (!userFound) {
    const newUser = new User({
      username: user.username,
      password: hashedPassword
    })
    await newUser.save()
    return newUser
  }
}

// Checks if a user exist in the database, then returns that user if found.
userModel.login = async (user) => {
  // Check if user exist.
  const userFound = await User.findOne({ username: user.username })
  if (userFound) {
    const passwordFound = await userModel.comparePassword(user.password, userFound.password)
    if (userFound) {
      if (passwordFound) {
        return userFound
      }
    }
  }
}

// Hashing a password with the password sent in to the function and returned hashed password.
userModel.passwordHash = async (password) => {
  const plainPassword = password
  const saltRounds = 10
  const hashedPassword = await bcrypt.hash(plainPassword, saltRounds)
  return hashedPassword
}

// Compares a password with a hashed password and returns true if it's the same password.
userModel.comparePassword = async (password, hashedPassword) => {
  const passwordFound = await bcrypt.compare(password, hashedPassword)
  return passwordFound
}

// userModel.reset = async () => {
//   await User.deleteMany()
// }
