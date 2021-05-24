data_list = []

def import_embedding_model(): #word2vec model

    from gensim.models import KeyedVectors
    import pickle
    word2vec_model = KeyedVectors.load('/data/data/com.sungkyul.barcode/files/chaquopy/AssetFinder/app/Data/kowiki_word2vec_wordvectors.data')#embedding model import
    print("word2vec model import")
    data_list.append(word2vec_model)
    #return word2vec_model

def import_recipe_vector(): #recipe_vector

    import pickle
    document_embedding_list = []
    with open("/data/data/com.sungkyul.barcode/files/chaquopy/AssetFinder/app/Data/document_embedding_list", "rb") as file: #recipe vector import
        temp = pickle.load(file)
        document_embedding_list = temp

    print("recipe_vec import ")
    data_list.append(document_embedding_list)

    #가중치 설정
def softmax(x):
    import numpy as np
    res = np.exp(x - np.max(x))
    return res/res.sum()

#상품명 평균백터 구하기
def vectors_pn(document_list):
    document_embedding_list = []
    docs_vec =None
    word2vec_model = data_list[0]
    count = 0
    print("document_list")
    print(document_list)
    #각 명사의 백터를 구한 뒤 더함
    for i in range(len(document_list)):
        index = 0
        weight_token = []
        weight_index = []
        for j in range(len(document_list[i])):
            if j == 0:
                for k in range(len(document_list[i])):
                    weight_index.append(k)
            weight_token = softmax(weight_index)
            #print(weight_token)
            count += 1
            #리스트의 모든 값을 불러와서 백터값을 구함
            try:
                if docs_vec is None:
                    docs_vec = word2vec_model[document_list[i][j]] * weight_token[j]
                else:
                    docs_vec = docs_vec + word2vec_model[document_list[i][j]] * weight_token[j]
            except KeyError as e:
                print(i)
                print(e)
                if docs_vec is None:
                    docs_vec = word2vec_model['물']
        #더한 명사의 양으로 나눔
        if docs_vec is not None:
            docs_vec = docs_vec / count
            #나눈 값을 저장 후 변수 초기화
            document_embedding_list.append(docs_vec)
            count = 0
            docs_vec = None
    return document_embedding_list

#상품명 벡터(이후 수정&추가)
def test(*input):
    res = None
    print("input = ")
    print(input)
    print(len(input))
    for i in range(len(input)):
        for j in range(len(input[i])):
            if(res is None):
                res = vectors_pn(input[i][j])[0]
                print(input[i])
            else:
                res = res + vectors_pn(input[i][j])[0]
                print(input[i])
    #for i in range(len(input)):
    #    if(res is None):
    #        res = vectors_pn(input[i])[0]
    #        print(vectors_pn(input[i])[0])
    #        print(input[i])
    #    else:
    #        res = res + vectors_pn((input[i]))[0]
    #        print(vectors_pn(input[i])[0])
    #        print(input[i])

    return [res]

#백터끼리의 유사도 계산
def compute_similarity(*input_list):
    #각 백터끼리의 코사인 유사도를 구함
    res_string = ""
    temp = [list(input_list)]
    document_embedding_list = data_list[1] #recipe_vector
    input_list_vec = vectors_pn(temp) #input_words
    print(input_list_vec)
    #input_list_vec = [test(*input_list)] #input_words

    from sklearn.metrics.pairwise import cosine_similarity
    cosine_similarities = cosine_similarity(input_list_vec, document_embedding_list)

    #상품명과 유사한 상위 5개 레시피 추출s
    sim_scores = list(enumerate(cosine_similarities[0]))###temp_a 쓸때 index = 0 아니면 pn_num
    sim_scores = sorted(sim_scores, key = lambda x: x[1], reverse = True)
    sim_scores = sim_scores[0:10]#출력할 레시피 수 지정
    #결과 확인
    result = []
    print(sim_scores)
    for i in range(10):
        out_temp = 0
        out_temp = sim_scores[i][0] + 1
        res_string = res_string + str(out_temp) + "/"
    return res_string
