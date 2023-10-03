setup:
	eval $(minikube -p minikube docker-env)

clean:setup
	kubectl delete -f ./notification-svc-deployment.yaml || true
	docker rmi shopnow/notification-svc || true

build: clean
	./gradlew build
	docker build -t shopnow/notification-svc:latest .

deploy: build
	kubectl apply -f ./notification-svc-deployment.yaml