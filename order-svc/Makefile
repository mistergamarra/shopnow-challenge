setup:
	eval $(minikube -p minikube docker-env)

clean:setup
	kubectl delete -f ./order-svc-deployment.yaml || true
	docker rmi shopnow/order-svc || true

build: clean
	./gradlew clean build
	docker build -t shopnow/order-svc:latest .

deploy: build
	kubectl apply -f ./order-svc-deployment.yaml