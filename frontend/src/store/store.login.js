import {fetchedLoginUserData,fetchedFindId,fetchedConfirmId,fetchedConfirmAuthNumber,fetchedSendAuthNumber,fetchedUpdatePassword} from "@/api/api.login";
import JwtService from "../common/jwt.service";


const loginStoreModule = {
  state    : {
    loginUserData  : [],
    loginErrors         : null,
    isAuthenticated: !!JwtService.getToken(),
    isFirstLogin: sessionStorage.getItem('isFirstLogin')
  },
  getters  : {
    fetchedLoginUserData (state) {
      return state.loginUserData
    },
    isAuthenticated (state) {
      return state.isAuthenticated
    },
  },
  mutations: {
    SET_LOGIN_DATA (state, loginUserData) {
      console.log(`SET_LOGIN_DATA !!`)
      state.isAuthenticated = true
      state.loginUserData = loginUserData
      const lutk = state.loginUserData.resultData.loginUserToken
      JwtService.saveToken(lutk)
      sessionStorage.setItem('userInfo',JSON.stringify(loginUserData.resultData))
      sessionStorage.setItem('isFirstLogin',loginUserData.resultData.firstLoginYn)
      state.isFirstLogin = loginUserData.resultData.firstLoginYn
    },
    SET_FIRST_LOGIN_DATA (state, loginUserData) {
      console.log(`SET_FIRST_LOGIN_DATA !!`)
      state.isAuthenticated = false
      state.loginUserData = loginUserData
      const lutk = state.loginUserData.resultData.loginUserToken
      JwtService.saveToken(lutk)
      sessionStorage.setItem('userInfo',JSON.stringify(loginUserData.resultData))
      state.isFirstLogin= loginUserData.resultData.firstLoginYn
    },
    SET_LOGIN_ERROR (state, error) {
      console.log(`SET_LOGIN_ERROR !!`)
      state.loginErrors = error
      
      kendo.alert(error)
    },
    SET_ISAUTHENTICATED (state){
      state.isAuthenticated = true
    }
  },
  actions  : {
    FETCH_LOGIN (context, credentials) {
      return new Promise(resolve => {
        fetchedLoginUserData(credentials).then(response => {
            
          const messageCode = response.data.resultStatus.messageCode
          
          //로그인 성공값이 아니라면 이유 불문하고 메시지 텍스트를 먼저 표시한다.
          if (messageCode !== '2000') {
              context.commit('SET_LOGIN_ERROR', response.data.resultStatus.messageText)
              
              return;
          }
          
          const firstLoginYn = response.data.resultData.firstLoginYn
            
          if (firstLoginYn === 'Y') {
            context.commit('SET_FIRST_LOGIN_DATA', response.data)
            resolve(response.data)
          } else if (firstLoginYn !== 'Y') {
            context.commit('SET_LOGIN_DATA', response.data)
            resolve(response.data)
          }
        }).catch(error => {
          console.log('==================== FETCH_LOGIN error ====================')
          console.log(error)
          context.commit('SET_LOGIN_ERROR', error)
        })
      })

    },
    FETCH_FIND_ID(context, credentials) {
      return new Promise(resolve => {
        fetchedFindId(credentials)
          .then(response => {
            if (response.data.resultStatus.messageCode === '2000') {

              resolve(response.data);
            } else {
              context.commit('SET_LOGIN_ERROR', response.data.resultStatus.messageText);
            }
          })
          .catch(error => {
            console.log("==================== error ====================");
            console.log(error);
            context.commit('SET_LOGIN_ERROR', error);
          });
      });

    },
    FETCH_CONFIRM_ID(context, credentials) {
      return new Promise(resolve => {
        fetchedConfirmId(credentials)
          .then(response => {
            if (response.data.resultStatus.messageCode === '2000') {

              resolve(response.data);
            } else {
              context.commit('SET_LOGIN_ERROR', response.data.resultStatus.messageText);
            }
          })
          .catch(error => {
            console.log("==================== error ====================");
            console.log(error);
            context.commit('SET_LOGIN_ERROR', error);
          });
      });

    },
    FETCH_CONFIRM_AUTH_NUMBER(context, credentials) {
      return new Promise(resolve => {
        fetchedConfirmAuthNumber(credentials)
          .then(response => {
            if (response.data.resultStatus.messageCode === '2000') {

              resolve(response.data);
            } else {
              context.commit('SET_LOGIN_ERROR', response.data.resultStatus.messageText);
            }
          })
          .catch(error => {
            console.log("==================== error ====================");
            console.log(error);
            context.commit('SET_LOGIN_ERROR', error);
          });
      });

    },
    FETCH_SEND_AUTH(context, credentials) {
      return new Promise(resolve => {
        fetchedSendAuthNumber(credentials)
          .then(response => {
            if (response.data.resultStatus.messageCode === '2000') {

              resolve(response.data);
            } else {
              context.commit('SET_LOGIN_ERROR', response.data.resultStatus.messageText);
            }
          })
          .catch(error => {
            console.log("==================== error ====================");
            console.log(error);
            context.commit('SET_LOGIN_ERROR', error);
          });
      });

    },
    FETCH_UPDATE_PASSWORD(context, credentials) {
      return new Promise(resolve => {
        fetchedUpdatePassword(credentials)
          .then(response => {
            if (response.data.resultStatus.messageCode === '2000') {

              resolve(response.data);
            } else {
              context.commit('SET_LOGIN_ERROR', response.data.resultStatus.messageText);
            }
          })
          .catch(error => {
            console.log("==================== error ====================");
            console.log(error);
            context.commit('SET_LOGIN_ERROR', error);
          });
      });

    }
  }
};

export default loginStoreModule;
