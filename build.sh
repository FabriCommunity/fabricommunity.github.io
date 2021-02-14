if [ ! -f SSG.jar ]; then
    sh ./download.sh
fi

java -jar SSG.jar build \
    --default-template section/index \
    --output-path build \
    --sources-path site \
    --template-path templates \
    --section doc

cp -R site/static build/static
