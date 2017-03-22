##Install kubectl
```
gcloud components install kubectl
```

For instructions how to setup kubectl with GKE cluster, see
https://cloud.google.com/container-engine/docs/clusters/operations

##Deploying C# frontend
```
kubectl create -f frontend-all.yaml
```

##Deploying Java backend
```
kubectl create -f backend-all.yaml
```


