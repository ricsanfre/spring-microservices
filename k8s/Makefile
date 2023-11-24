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
	kubectl -n rabbitmq get secret rabbitmqcluster-default-user -o jsonpath="{.data.username}" | base64 --decode; echo
	kubectl -n rabbitmq get secret rabbitmqcluster-default-user -o jsonpath="{.data.password}" | base64 --decode; echo

configure-postgres:
	kubectl exec -it postgres-0 -n postgres -- psql -U ricsanfre

rabbitmq-portforwarding:
	kubectl port-forward svc/rabbitmqcluster 15672:15672 -n rabbitmq

zipkin-portforwarding:
	kubectl port-forward svc/zipkin 9411:9411 -n zipkin