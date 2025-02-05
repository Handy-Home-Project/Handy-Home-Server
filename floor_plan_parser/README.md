### docker image and parameter file 위치 
##### https://drive.google.com/drive/folders/1p93ZkJ8Me3t791aphvemmbhr2T8c-2ti?usp=sharing
##### 다운로드 후 압축해제 docker load 사용. - 구동방법 참조

### docker command line
```bash
docker run --name spa -it --rm --mount type=bind,source={dataset folder},target={dataset folder} floorplan1:latest
```
* example
```bash
docker run --name spa -it --rm --mount type=bind,source=/Users/parkyeseung/IdeaProjects/handy_home/floor_plan_parser,target=/c/Users/USER/handy_home floorplan1:latest
```


### python 실행 방법
* 도커 실행후, python 실행 파일 위치로 이동, 터미널에서 실행
```bash
python spa_evaluation_2.py -fmp {paramiter file path}
                      -dt {예측하고자 하는 이미지 path}
                      -rt {output folder default = ./rslt}

```
* example
```bash
python spa_evaluation_2.py -fmp ./model/SPA_FP_best_model.pth \
                      -dt ./data/test_image.png \
                      -rt ./output

```
