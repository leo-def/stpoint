(function() {
    "use strict";
    angular.module('app',
            [
                'ngCookies',
                'base64',
                'reTree',
                'ng.deviceDetector',
                'utils',
                'ngRoute',
                'ngAnimate',
                'ngSanitize',
                'ngMask',
                'ngMaterial',
                'ngFileUpload',
                //app
                'app.page',
                'app.auth',
                'app.situation',
                'app.user',
                'app.expression',
                'app.admin',
                'app.resetPassword',
                'app.confirmAccount',
                'app.admin.situation',
                'app.admin.user',
                'app.admin.expression',
                'app.admin.value'])
            .config(locationConfig)
            .config(routeConfig)
            .config(angularMaterialConfig)
            .config(dataFactoryConfig);

    function locationConfig($locationProvider, $httpProvider) {
        if (window.history && window.history.pushState) {
            $locationProvider.html5Mode(true);
        }
        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
        //$httpProvider.defaults.xsrfCookieName = 'XSRF-TOKEN';//$httpProvider.defaults.xsrfHeaderName = 'X-XSRF-TOKEN';
    }
    function routeConfig($routeProvider) {

        var redirect = {
            title: 'Bem vindo',
            template: '<h1>Bem Vindo</h1>',
            controller: 'redirectController',
            controllerAs: 'vm'
        };
        $routeProvider
                .when('/', redirect)
                .when('/alert/:message', redirect);

        }
    function angularMaterialConfig($mdThemingProvider, $mdDateLocaleProvider) {
        $mdThemingProvider.theme('customTheme')
                .primaryPalette('green')
                .accentPalette('amber')
                .warnPalette('red');
        $mdThemingProvider.theme('defaultTheme')
                .primaryPalette('grey')
                .accentPalette('light-green')
                .warnPalette('red');
        $mdThemingProvider.setDefaultTheme('defaultTheme');
        $mdDateLocaleProvider.formatDate = function(date) {
            return moment(date).format('DD/MM/YYYY');
        };
        $mdDateLocaleProvider.parseDate = function(dateString) {
            var m = moment(dateString, 'DD/MM/YYYY', true);
            return m.isValid() ? m.toDate() : new Date(NaN);
        };
    }

    function dataFactoryConfig($httpProvider) {

        $httpProvider.interceptors.push(function(authTokenFactory) {
            return {
                "request": function(config) {
                    if (authTokenFactory.isAuth()) {
                        config.headers[authTokenFactory.authTokenHeader] = authTokenFactory.token();
                    }
                    return config;
                }
            };
        });
    }
})();


               /*
                 $routeProvider
                 .when('/',{
                 redirectTo: '/home'
                 });

                 * .otherwise({
                 redirectTo: '/applications'
                 });
                 */

