


## PostgreSQL

-  Deploy PostgreSQL
  ```shell
  kubectl apply -f bootstrap/postgres
  ``` 

- Connect to PosgreSQL POD

  ```shell
  kubectl exec -it postgres-0 -- psql -U ricsanfre
  ```
   
