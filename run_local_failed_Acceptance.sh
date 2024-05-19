#!/bin/bash
mvn test -Dbrowser=remote-chrome -Denvironment=local -Dtest=RunAcceptance.java