setup:
	eval $(minikube -p minikube docker-env)

clean:setup
	kubectl delete -f ./account-svc-deployment.yaml || true
	docker rmi shopnow/account-svc || true

build: clean
	./gradlew build
	docker build -t shopnow/account-svc:latest .

deploy: build
	kubectl apply -f ./account-svc-deployment.yaml