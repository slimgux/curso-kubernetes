# curso-kubernetes
No maneja variables de ambiente, solo se configura en el archivo de configuración de kubernetes
kubectl create deployment mysql8 --image=mysql:8.0.31 --port=3306

Se creq el deployment archivo
kubectl create deployment mysql8 --image=mysql:8.0.31 --port=3306 --dry-run=client -o yaml > deployment-mysql8.yaml
kubectl apply ./deployment-mysql8.yaml

Ahora hay que crear el servicio
ClusterIP --> solo se puede acceder desde dentro del cluster.
NodePort --> se puede acceder desde fuera del cluster. solo una maquina
LoadBalancer --> se puede acceder desde fuera del cluster y se le asigna una ip publica varias maquinas.
Forma declarativa:
kubectl expose deployment mysql8 --type=ClusterIP --port=3306 --dry-run=client -o yaml > service-mysql8.yaml
Forma imperativa:
kubectl expose deployment mysql8 --type=ClusterIP --port=3306

Forma declarativa:
kubectl create deployment usuarios --image=grangux/usuarios:latest --port=8001 --dry-run=client -o yaml > deployment-usuarios.yaml
Forma imperativa
kubectl create deployment usuarios --image=grangux/usuarios:latest --port=8001

kubectl expose deployment usuarios --type=LoadBalancer --port=8001

En minikube para ip externa se usa: minikube service usuarios --url

actualizar imagen:
kubectl set image usuarios usuarios=grangux/usuarios:latest

kubectl scale deployment usuarios --replicas=3

Hacemos para postgres:
kubectl create deployment postgres14 --image=postgres:14-alpine --port=5432 --dry-run=client -o yaml > deployment-postgres14.yaml
kubectl expose deployment postgres14 --type=ClusterIP --port=5432 --dry-run=client -o yaml > service-postgres14.yaml

Hacemos para cursos:
kubectl create deployment cursos --image=grangux/cursos:latest --port=8001 --dry-run=client -o yaml > deployment-cursos.yaml
kubectl expose deployment cursos --type=LoadBalancer --port=8002 --dry-run=client -o yaml > service-cursos.yaml