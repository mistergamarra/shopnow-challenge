setup:
	eval $(minikube -p minikube docker-env)

clean:setup
	docker rmi shopnow/order-svc || true
	kubectl delete -f ./order-svc-deployment.yaml || true

build: clean
	./gradlew build
	docker build -t shopnow/order-svc:latest .

deploy: build
	kubectl apply -f ./order-svc-deployment.yaml