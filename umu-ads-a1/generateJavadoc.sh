rm -r -f /tmp/docs
mkdir /tmp/docs
mkdir /tmp/docs/src

cp -r src /tmp/docs

cd /tmp/docs/src
#javadoc -d /tmp/docs/ads -subpackages \
#        se.umu.cs.ads.a1 \
#        se.umu.cs.ads.a1.adts \
#        se.umu.cs.ads.a1.backend \
#        se.umu.cs.ads.a1.clients \
#        se.umu.cs.ads.a1.interfaces \
#        se.umu.cs.ads.a1.types \
#        se.umu.cs.ads.a1.util
find . -type f -name "*.java" | xargs javadoc -d /tmp/docs/ads/docs

cd /tmp/docs
zip -r umu-ads-a1-docs.zip ads
