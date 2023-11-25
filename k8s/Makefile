.EXPORT_ALL_VARIABLES:
create-k3d:
	k3d cluster create mycluster -c k3d-cluster.yaml

delete-k3d:
	k3d cluster delete mycluster

helm-setup:
	helm repo add bitnami https://charts.bitnami.com/bitnami
	helm repo update

install-postgresql:
	kubectl create namespace postgres
	kubectl apply -f bootstrap/postgres

install-zipkin:
	kubectl create namespace zipkin
	kubectl apply -f bootstrap/zipkin

install-rabbitmq-operator:
	kubectl create namespace rabbitmq
	helm install rabbit-mq-operator bitnami/rabbitmq-cluster-operator -n rabbitmq
deploy-rabbitmq:
	kubectl apply -f bootstrap/rabbitmq/rabbitmq-cluster.yaml

get-rabbitmq-admin-credentials:
	kubectl -n rabbitmq get secret rabbitmq-default-user -o jsonpath="{.data.username}" | base64 --decode; echo
	kubectl -n rabbitmq get secret rabbitmq-default-user -o jsonpath="{.data.password}" | base64 --decode; echo

# TODO: Configure creation of databases customer
configure-postgres:
	kubectl exec -it postgres-0 -n postgres -- psql -U ricsanfre \
		-c '\set AUTOCOMMIT on\ndrop database if exists customer; create database customer;' \
		-c '\set AUTOCOMMIT on\ndrop database if exists notification; create database notification' \
		-c '\set AUTOCOMMIT on\ndrop database if exists fraud; create database fraud;'

# Additional user need to be created. guest user is not allowed to have remote connections
configure-rabbitmq:
	kubectl exec -it rabbitmq-server-0 -n rabbitmq -- rabbitmqctl add_user ricsanfre password
	kubectl exec -it rabbitmq-server-0 -n rabbitmq -- rabbitmqctl set_user_tags ricsanfre administrator
	kubectl exec -it rabbitmq-server-0 -n rabbitmq -- rabbitmqctl set_permissions ricsanfre ".*" ".*" ".*"


rabbitmq-portforwarding:
	kubectl port-forward svc/rabbitmq 15672:15672 -n rabbitmq

zipkin-portforwarding:
	kubectl port-forward svc/zipkin 9411:9411 -n zipkin

deploy-services:
	kubectl create namespace services
	kubectl apply -f services/customer
	kubectl apply -f services/fraud
	kubectl apply -f services/notification

customer-portforwarding:
	kubectl port-forward svc/customer 8081:80 -n services