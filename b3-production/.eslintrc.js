module.exports = {
  env: {
    browser: true,
    es2021: true,
    node: true
  },
  plugins: [
    'jsdoc'
  ],
  extends: [
    'standard',
    'plugin:jsdoc/recommended'
  ],
  overrides: [
    {
      files: ['*.mjs'],
      parserOptions: {
        ecmaVersion: 'latest',
        sourceType: 'module'
      }
    }
  ],
  parserOptions: {
    ecmaVersion: 'latest',
    sourceType: 'module'
  },
  rules: {
  },
  ignorePatterns: [
    'build/',
    'doc/',
    'dist/',
    'node_modules/'
  ],
  globals: {
    idArray: 'readonly',
    titleArray: 'readonly',
    descriptionArray: 'readonly',
    stateArray: 'readonly',
    createdArray: 'readonly',
    updatedArray: 'readonly',
    labelsArray: 'readonly',
    milestoneArray: 'readonly',
    authorArray: 'readonly',
    backToArray: 'readonly',
    flashMessage: 'readonly',
    nameArray: 'readonly'
  }
}
