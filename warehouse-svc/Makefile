setup:
	eval $(minikube -p minikube docker-env)

clean:setup
	kubectl delete -f ./warehouse-svc-deployment.yaml || true
	docker rmi shopnow/warehouse-svc || true

build: clean
	./gradlew build
	docker build -t shopnow/warehouse-svc:latest .

deploy: build
	kubectl apply -f ./warehouse-svc-deployment.yaml