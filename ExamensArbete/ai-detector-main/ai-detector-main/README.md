# AI Detector


## Getting started

1. Download `.gitlab-ci.yml` and the folder located in [this repository](https://gitlab.com/wrindebrant/ai-detection-template).

2. Upload the folder and the `.gitlab-ci.yml` file to the project you want to use it in.

3. In your project, navigate to `Settings > CI/CD > Variables`. Add two variables:

__Variable 1__  
```
Protected variable          [ ]  
Mask variable               [X]  
Expand variable reference   [X]  
Key = COPYLEAKS_EMAIL  
Value = <Your Copyleaks Email>  
```

__Variable 2__   
```
Protected variable          [ ]  
Mask variable               [X]  
Expand variable reference   [X]  
Key = COPYLEAKS_KEY  
Value = <Your Copyleaks API Access Credential Key>
```


4. If you're using version `1.0.0` in the `.gitlab-ci.yml` file, the pipeline will trigger on every commit.  
If you're using version `1.0.1` in the `.gitlab-ci.yml` file, the pipleline will only trigger when you create a new tag.  
If you're using version `1.0.2` in the `.gitlab-ci.yml` file, the pipeline will run if you create a merge request.  
If you're using version `1.0.3` in the `.gitlab-ci.yml` file, the pipeline will run if you either, create a merge request, create a new tag, or make a commit.

5. You find the result of the pipeline in `Build > Pipeline > "The logs from the job that was executed"`.