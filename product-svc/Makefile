setup:
	eval $(minikube -p minikube docker-env)

clean:setup
	docker rmi shopnow/product-svc || true
	kubectl delete -f ./product-svc-deployment.yaml || true

build: clean
	./gradlew build
	docker build -t shopnow/product-svc:latest .

deploy: build
	kubectl apply -f ./product-svc-deployment.yaml