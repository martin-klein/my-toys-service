In order to install and run this application the following commands need to be executed:\
- git clone https://github.com/martin-klein/my-toys-service.git
- cd my-toys-service/
- sudo docker build -t my-toys-image-complete . && sudo docker run -p 80:80 my-toys-image-complete

It is assumed that git and docker are already installed.