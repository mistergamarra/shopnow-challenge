setup:
	eval $(minikube -p minikube docker-env)

clean:setup
	docker rmi shopnow/gateway|| true
	kubectl delete -f ./gateway-deployment.yaml || true

build: clean
	./gradlew clean build
	docker build -t shopnow/gateway:latest .

deploy: build
	kubectl apply -f ./gateway-deployment.yaml