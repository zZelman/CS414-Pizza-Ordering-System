#!/bin/bash

git pull

git status

echo

read -p "Continue? [y/n] " -n 1 -r

echo

if [[ $REPLY =~ ^[Yy]$ ]]
then
	git add --all .
	git commit -m "$*"
	git push
fi
