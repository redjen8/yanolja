const path = require('path');

module.exports = {
    outputDir: path.resolve(__dirname, "../" + "src/main/resources/static"),
    devServer: {
        proxy: {
            '/api': {
                target: 'localhost:9000',
                ws: true,
                changeOrigin: true
            },
        }
    }
}