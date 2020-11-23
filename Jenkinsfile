#!/usr/bin/env groovy

node {
	stage('checkout')
	{
		checkout scm
	}

	stage('check java , node , docker , docker-compose')
	{
		try
		{
			sh "pwd"
			sh "java -version"
			sh "node -v"
			sh "npm -v"
			sh "docker -v"
			sh "docker-compose -v"
			sh "docker service ls"
		}
		catch (exception)
		{
			throw exception
		}
	}

	stage('clean')
	{
		sh "chmod +x gradlew"
		sh "./gradlew clean --no-daemon"
	}

	stage('nohttp')
	{
		sh "./gradlew checkstyleNohttp --no-daemon"
	}

	stage('check integration')
	{
		try
		{
			sh 'docker service inspect catiny-services_catinymessenger-cassandra'
			sh 'docker service inspect catiny-services_catinymessenger-redis'
			sh "docker service inspect catiny-services_jhipster-registry"
			sh "docker service inspect catiny-services_zookeeper"
			sh "docker service inspect catiny-services_kafka"
		}
		catch (ignored)
		{
			echo 'the necessary services are not running . try start it'
			sh "docker-compose -f src/main/docker/app-prod.yml up -d"
			echo 'Sleep for 80 seconds to wait for the cassandra to be ready'
			sleep(80)
		}

		try
		{
			sh 'docker service inspect catinydev-services_catinydevmessenger-cassandra'
			sh 'docker service inspect catinydev-services_catinydevmessenger-redis'
			sh "docker service inspect catinydev-services_jhipster-registry-dev"
			sh "docker service inspect catinydev-services_zookeeper-dev"
			sh "docker service inspect catinydev-services_kafka-dev"
		}
		catch (ignored)
		{
			echo 'the necessary services are not running . try start it'
			sh "docker-compose -f src/main/docker/app-prod.yml up -d"
			echo 'Sleep for 80 seconds to wait for the cassandra to be ready'
			sleep(80)
		}
	}

	stage('check catiny-uaa')
	{
		try
		{
			sh "docker service inspect catiny-app-prod_catinyuaa"
		}
		catch (err)
		{
			echo "Warning catiny-app-prod_catinyuaa is not running. try start catinyuaa"
//			sh "docker-compose -f /var/lib/jenkins/workspace/CatinyUAA_master/src/main/docker/catiny-uaa.yml up -d"
//			throw err
		}
	}

	stage('backend tests') {
		try
		{
//			sh "./gradlew test integrationTest -PnodeInstall --no-daemon"
			sh "./gradlew build"
		}
		catch (err)
		{
			throw err
		}
		finally
		{
			junit '**/build/**/TEST-*.xml'
		}
	}

	stage('build docker catiny-messenger')
	{
		try
		{
			sh "./gradlew bootJar -Pprod jibDockerBuild --no-daemon"
			sh "docker tag catinymessenger:latest yuvytung/catinymessenger:latest"
			sh "docker push yuvytung/catinymessenger:latest"
		}
		catch (exception)
		{
			throw exception
		}
	}

	stage('start docker catiny-messenger')
	{
		try
		{
			sh "docker service update --force catiny-app-dev_catinymessenger"
			sleep(60)
			sh "docker service logs --tail 1000 catiny-app-dev_catinymessenger"
		}
		catch (exception)
		{
			throw exception
		}
		sleep(60)
		sh "docker service update --force catiny-app-prod_catinymessenger"
		echo "Successful deployment"
	}

	stage('Log display after 200 seconds from running')
	{
		sleep(60)
		sh "docker service logs --tail 1000 catiny-app-dev_catinymessenger"
		sh "docker service logs --tail 1000 catiny-app-prod_catinymessenger"
	}
}
//#!/usr/bin/env groovy
//
//node {
//	stage('checkout')
//	{
//		checkout scm
//	}
//
//	stage('check java , node , docker , docker-compose')
//	{
//		sh "pwd"
//		sh "java -version"
//		sh "node -v"
//		sh "npm -v"
//		sh "docker -v"
//		sh "docker-compose -v"
//	}
//
//	stage('clean')
//	{
//		sh "chmod +x gradlew"
//		sh "./gradlew clean --no-daemon"
//	}
//
//	stage('check integration')
//	{
//		try
//		{
//			sh 'docker container inspect docker_catinymessenger-cassandra_node_1'
//			sh 'docker container inspect docker_catinymessenger-cassandra_1'
//			sh 'docker container inspect docker_catinymessenger-redis_1'
//			sh "docker container inspect docker_jhipster-registry_1"
//			sh "docker container inspect docker_zookeeper_1"
//			sh "docker container inspect docker_kafka_1"
//		}
//		catch (ignored)
//		{
//			echo 'the necessary services are not running . try start it'
//			sh "docker-compose -f src/main/docker/app-prod.yml up -d"
//			echo 'Sleep for 80 seconds to wait for the cassandra to be ready'
//			sleep(80)
//		}
//	}
//
//	stage('check catiny-uaa')
//	{
//		try
//		{
//			sh "docker container inspect docker_catinyuaa-app_1"
//		}
//		catch (err)
//		{
//			echo "docker_jhipster-registry_1 is not running. try start catinyuaa"
////			sh "docker-compose -f /var/lib/jenkins/workspace/CatinyUAA_master/src/main/docker/catiny-uaa.yml up -d"
//			throw err
//		}
//	}
//
//	stage('nohttp')
//	{
//		sh "./gradlew checkstyleNohttp --no-daemon"
//	}
//
//	stage('backend tests') {
//		try
//		{
//			sh "./gradlew test integrationTest -PnodeInstall --no-daemon"
//		}
//		catch (err)
//		{
//			throw err
//		}
//		finally
//		{
//			junit '**/build/**/TEST-*.xml'
//		}
//	}
//
//	stage('build docker catiny-messenger')
//	{
//		sh "./gradlew bootJar -Pprod jibDockerBuild --no-daemon"
//	}
//
//	stage('start docker catiny-messenger')
//	{
//		sh "docker-compose -f src/main/docker/catiny-messenger.yml up -d"
//		echo "Successful deployment"
//	}
//
//	stage( 'Log display after 200 seconds from running')
//	{
//		sleep(60)
//		sh "docker logs docker_catinymessenger-app_1 --tail 1000"
//	}
//}
