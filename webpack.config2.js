var path = require('path');

module.exports = {
    entry: './src/main/resources/static/js/app.js',
    devtool: 'sourcemaps',
    cache: false,
    mode: 'development',
    output: {
        path: __dirname,
        filename: './src/main/resources/static/built/bundle.js'
    },
    module: {
        rules: [
            {
                test: path.join(__dirname, '.'),
                exclude: /(node_modules)/,
                use: [{
                    loader: 'babel-loader',
                    options: {
                        presets: ["@babel/preset-env", "@babel/preset-react",
                                  {"plugins": [
                                      "@babel/plugin-proposal-class-properties"
                                    ]}
                        ]
                    }
                }
                ]
            },
            //other rule
            {
            	test: /\.css$/,
            	use: [ 'style-loader', 'css-loader' ]
        	},//other rule
            {
                test: /\.(js|jsx)$/,
                exclude: /node_modules/,
                use: ["babel-loader"],            
              }
        ]//ends of rules
    }
              
};

/*

{
test: /\.(js|jsx)$/,
exclude: /node_modules/,
use: ["babel-loader"],            
},
{
test: /\.css$/,
use: [ 'style-loader', 'css-loader' ]
}

*/