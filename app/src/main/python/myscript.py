#라이브러리 등록
def main():
    import gensim
    import sklearn
    import os
    print(os.getcwd())
    print("libary import")

#주소지정
def set_address():
    from os.path import dirname, join
    f = open(join(dirname('src/main/python/Data'), "filename.txt"), 'r')
    f.close()

def import_embedding_model():
    from gensim.models import KeyedVectors
    from os.path import dirname, join
    filename = join(dirname('src/main/python/Data'), "kowiki_word2vec_wordvectors")
    word2vec_model = KeyedVectors.load(filename)
    print(filename)

def test(a):
    f = open(a, 'r')
    f.close()
    return f

#상품명 평균백터 구하기
def vectors_pn(document_list):
    document_embedding_list = []
    docs_vec =None
    count = 0
    #각 명사의 백터를 구한 뒤 더함
    for i in range(len(document_list)):
        index = 0
        weight_token = 0
        for j in range(len(document_list[i])):
            index = len(document_list[i])
            weight_token = 1 / index
            count += 1
            #리스트의 모든 값을 불러와서 백터값을 구함
            if docs_vec is None:
                docs_vec = word2vec_model[document_list[i][j]] * weight_token * (count)
            else:
                docs_vec = docs_vec + word2vec_model[document_list[i][j]] * weight_token * (count)
        #더한 명사의 양으로 나눔
        if docs_vec is not None:
            docs_vec = docs_vec / count
            #나눈 값을 저장 후 변수 초기화
            document_embedding_list.append(docs_vec)
            count = 0
            docs_vec = None
    return document_embedding_list


#상품명/식재료명 등록
#def set_input_values():

#백터끼리의 유사도 계산
def compute_similarity(input_list, embedding_list):
    #각 백터끼리의 코사인 유사도를 구함
    from sklearn.metrics.pairwise import cosine_similarity
    cosine_similarities = cosine_similarity(input_list, document_embedding_list)

#def print_result():
    #상품명과 유사한 상위 5개 레시피 추출s
    sim_scores = list(enumerate(cosine_similarities[0]))###temp_a 쓸때 index = 0 아니면 pn_num
    sim_scores = sorted(sim_scores, key = lambda x: x[1], reverse = True)
    sim_scores = sim_scores[0:6]#출력할 레시피 수 지정
    #r결과 확인
    print()
    for i in range(6):
        print(sim_scores[i][0])