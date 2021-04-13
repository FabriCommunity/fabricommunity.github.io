if [ ! -f SSG.jar ]; then
    sh ./download.sh
fi

java -jar SSG.jar serve \
    --output-path build
